package top.oatnil.undercontrol.account.controller

import org.springframework.web.bind.annotation.*
import top.oatnil.undercontrol.account.application.AccountApplicationService
import top.oatnil.undercontrol.account.application.CreateCommand
import top.oatnil.undercontrol.account.application.UpdateCommand
import top.oatnil.undercontrol.account.controller.response.AccountDetailsResponse
import top.oatnil.undercontrol.account.controller.response.AccountHomePageVO
import top.oatnil.undercontrol.account.controller.response.AvailableAccountResponse
import top.oatnil.undercontrol.account.domain.Account
import top.oatnil.undercontrol.account.domain.AccountTransferCommand
import top.oatnil.undercontrol.account.domain.commands.AccountDepositCommand
import top.oatnil.undercontrol.account.domain.commands.AccountWithdrawCommand

@RestController
@RequestMapping("/accounts")
class AccountController(
    val applicationService: AccountApplicationService
) {

    @PostMapping
    fun create(@RequestBody command: CreateCommand): String {
        return applicationService.create(command)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): Account {
        return applicationService.get(id)
    }

    @GetMapping
    fun findAllAccounts(): List<Account> = applicationService.findAll()

    @GetMapping("/availableAccounts")
    fun findAvailableAccounts(@RequestParam excludeAccountIds: Set<String>): List<AvailableAccountResponse> {
        return applicationService.findAvailableAccount(excludeAccountIds)
    }

    @GetMapping("/home-page")
    fun accountHomePage(): AccountHomePageVO = applicationService.homePage()

    @PostMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody command: UpdateCommand) {
        return applicationService.update(id, command)
    }

    @GetMapping("/{accountId}/details")
    fun getDetails(@PathVariable accountId: String): AccountDetailsResponse {
        return applicationService.getDetails(accountId)
    }

    @PostMapping("/transfer")
    fun transfer(@RequestBody request: AccountTransferCommand) {
        return applicationService.transfer(request)
    }

    @PostMapping("/deposit")
    fun deposit(@RequestBody request: AccountDepositCommand) {
        return applicationService.deposit(command = request);
    }

    @PostMapping("/withdraw")
    fun withdraw(@RequestBody request: AccountWithdrawCommand) {
        return applicationService.withdraw(command = request);
    }

}

