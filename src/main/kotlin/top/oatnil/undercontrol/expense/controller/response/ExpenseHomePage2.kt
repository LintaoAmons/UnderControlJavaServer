package top.oatnil.undercontrol.expense.controller.response

import top.oatnil.undercontrol.expense.domain.Expense
import java.math.BigDecimal

data class ExpenseHomePage2(
    val totalExpense: BigDecimal,
    val dailyExpenses: List<DailyExpenses>
) {
    companion object {
        fun from(expenses: Collection<Expense>): ExpenseHomePage2 {
            return ExpenseHomePage2(
                totalExpense = expenses.sumOf { it.amount },
                dailyExpenses = expenses.groupBy { it.domainFields.createdAt.toLocalDate() }
                    .map { DailyExpenses.from(it.value, it.key) }
                    .sortedByDescending { it.date }
            )
        }
    }
}
