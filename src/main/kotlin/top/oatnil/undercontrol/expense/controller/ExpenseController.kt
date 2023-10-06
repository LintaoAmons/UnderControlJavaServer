package top.oatnil.undercontrol.expense.controller

import org.springframework.web.bind.annotation.*
import top.oatnil.undercontrol.common.UserContext
import top.oatnil.undercontrol.expense.application.CreateExpenseCommand
import top.oatnil.undercontrol.expense.application.ExpenseApplicationService
import top.oatnil.undercontrol.expense.application.UpdateExpenseCommand
import top.oatnil.undercontrol.expense.controller.response.ExpenseHomePage2
import top.oatnil.undercontrol.expense.domain.Expense
import java.time.Month

@RestController
@RequestMapping("/expense")
class ExpenseController(
    val applicationService: ExpenseApplicationService
) {
    @GetMapping
    fun findAllExpense(): List<Expense> {
        return applicationService.findByUserId(UserContext.getCurrentUser())
    }

    @PostMapping("/{id}/delete")
    fun delete(@PathVariable id: String) = applicationService.delete(id)

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = applicationService.getExpense(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody updateExpenseCommand: UpdateExpenseCommand) {
        applicationService.update(id, updateExpenseCommand)
    }

    @PostMapping
    fun createExpense(@RequestBody createExpenseCommand: CreateExpenseCommand) =
        applicationService.createExpense(createExpenseCommand)

    @GetMapping("/home-page")
    fun expenseOfMonth(month: Month): ExpenseHomePage2 {
        return applicationService.homePageOfMonth(UserContext.getCurrentUser(), month)
    }
}