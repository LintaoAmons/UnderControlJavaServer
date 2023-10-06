package top.oatnil.undercontrol.account.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import top.oatnil.undercontrol.account.controller.response.AccountDetailsResponse
import top.oatnil.undercontrol.account.controller.response.AccountHomePageVO
import top.oatnil.undercontrol.account.controller.response.AccountResponse.Companion.toResponse
import top.oatnil.undercontrol.account.controller.response.AvailableAccountResponse
import top.oatnil.undercontrol.account.controller.response.AvailableAccountResponse.Companion.toAvailableAccountResponse
import top.oatnil.undercontrol.account.domain.*
import top.oatnil.undercontrol.account.domain.commands.AccountDepositCommand
import top.oatnil.undercontrol.account.domain.commands.AccountWithdrawCommand
import top.oatnil.undercontrol.base.DomainResourceUtil
import top.oatnil.undercontrol.common.UserContext
import top.oatnil.undercontrol.common.logger

@Service
class AccountApplicationService(
    val accountService: AccountService,
    val accountRepository: AccountRepository,
    val changeRecordRepository: ChangeRecordRepository,
    val transferRecordRepository: TransferRecordRepository
) {

    fun create(command: CreateCommand): String {
        return accountRepository.save(command.toAccount())
    }

    fun get(id: String): Account {
        val account = accountRepository.findById(id) ?: throw RuntimeException("can't find by id: [${id}]")
        DomainResourceUtil.checkOwnership(account)
        return account
    }

    fun findAll(): List<Account> = accountService.findAll()

    fun findAvailableAccount(excludeAccountIds: Set<String>): List<AvailableAccountResponse> {
        return accountService.findAll()
            .filter { it.id !in excludeAccountIds }
            .map { it.toAvailableAccountResponse() }
    }

    @Transactional
    fun update(id: String, command: UpdateCommand) {
        val old = accountRepository.findById(id) ?: throw RuntimeException("No such account")
        DomainResourceUtil.checkOwnership(old)
        accountRepository.save(command.toAccount(old))
        changeRecordRepository.save(command.toChangeRecord(id))
    }

    fun getDetails(id: String): AccountDetailsResponse {
        val account = accountRepository.findById(id) ?: throw RuntimeException("No such account")
        DomainResourceUtil.checkOwnership(account)
        val records = changeRecordRepository.findByAccountId(id)
        return AccountDetailsResponse(account.toResponse(), records)
    }

    fun homePage(): AccountHomePageVO {
        logger().info("Get home page for {}", UserContext.getCurrentUser())

        val accounts = accountService.findAll()
        return AccountHomePageVO(
            accounts.sumOf { it.amount },
            accounts.map { it.toResponse() }
        )
    }

    @Transactional
    fun transfer(command: AccountTransferCommand) {
        accountService.transfer(command)
        transferRecordRepository.save(command.toTransRecord())
    }

    fun deposit(command: AccountDepositCommand) {
        accountService.deposit(command)
    }

    fun withdraw(command: AccountWithdrawCommand) {
        accountService.withdraw(command)
    }
}