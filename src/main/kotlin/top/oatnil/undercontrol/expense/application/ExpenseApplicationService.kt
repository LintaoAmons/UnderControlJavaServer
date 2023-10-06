package top.oatnil.undercontrol.expense.application

import org.springframework.stereotype.Service
import top.oatnil.undercontrol.common.UserContext
import top.oatnil.undercontrol.expense.controller.response.ExpenseHomePage2
import top.oatnil.undercontrol.expense.controller.response.ExpenseResponse
import top.oatnil.undercontrol.expense.controller.response.ExpenseResponse.Companion.toResponse
import top.oatnil.undercontrol.expense.domain.Expense
import top.oatnil.undercontrol.expense.domain.ExpenseRepository
import java.time.Month

@Service
class ExpenseApplicationService(
    val expenseRepository: ExpenseRepository
) {
    fun findAll(): List<Expense> {
        return expenseRepository.findAll()
    }

    fun createExpense(createExpenseCommand: CreateExpenseCommand) {
        return expenseRepository.save(createExpenseCommand.toExpense())
    }

    fun findByUserId(userId: String): List<Expense> {
        return expenseRepository.findAllByUserId(userId)
    }

    fun homePageOfMonth(userId: String, month: Month): ExpenseHomePage2 {
        return ExpenseHomePage2.from(
            expenseRepository.findAllByUserIdAndMonth(userId, month)
        )
    }

    fun delete(id: String) {
        expenseRepository.delete(id)
    }

    fun update(id: String, updateExpenseCommand: UpdateExpenseCommand) {
        val current = expenseRepository.findById(id) ?: throw RuntimeException("Not found by id $id")
        updateExpenseCommand.toExpense(current).also { expenseRepository.save(it) }
    }

    fun getExpense(id: String): ExpenseResponse {
        return expenseRepository.get(id).toResponse()
    }
}

