package top.oatnil.undercontrol.account.domain

import org.springframework.stereotype.Service
import top.oatnil.undercontrol.account.domain.commands.AccountDepositCommand
import top.oatnil.undercontrol.account.domain.commands.AccountWithdrawCommand
import top.oatnil.undercontrol.base.DomainResourceUtil
import top.oatnil.undercontrol.common.UserContext

@Service
class AccountService(
    val accountRepository: AccountRepository
) {
    fun transfer(command: AccountTransferCommand) {
        val accounts = accountRepository.findAllByIds(command.accountIds())
        DomainResourceUtil.checkOwnership(accounts)
        val currentAccount = accounts.find { it.id == command.currentAccountId }!!
        val targetAccount = accounts.find { it.id == command.targetAccountId }!!

        when (command.transferType) {
            TransferType.IN -> {
                currentAccount.deposit(amount = command.amount)
                targetAccount.withdraw(amount = command.amount)
            }
            TransferType.OUT -> {
                currentAccount.withdraw(amount = command.amount)
                targetAccount.deposit(amount = command.amount)
            }
        }

        accountRepository.save(listOf(currentAccount, targetAccount))
    }

    fun deposit(command: AccountDepositCommand) {
        accountRepository.getById(command.accountId)
            .also { DomainResourceUtil.checkOwnership(it) }
            .also { it.deposit(command.amount) }
            .let { accountRepository.save(it) }
    }

    fun withdraw(command: AccountWithdrawCommand) {
        accountRepository.getById(command.accountId)
            .also { DomainResourceUtil.checkOwnership(it) }
            .also { it.withdraw(command.amount) }
            .let { accountRepository.save(it) }
    }

    fun findAll(): List<Account> {
        return accountRepository.findAllByUserId(UserContext.getCurrentUser())
    }
}