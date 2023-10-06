package top.oatnil.undercontrol.expense.controller.response

import top.oatnil.undercontrol.expense.domain.Expense
import java.math.BigDecimal

data class ExpenseHomePage(
    val totalExpense: BigDecimal,
    val expenses: List<Expense>
)
