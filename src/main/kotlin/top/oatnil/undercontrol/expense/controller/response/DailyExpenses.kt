package top.oatnil.undercontrol.expense.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import top.oatnil.undercontrol.expense.controller.response.ExpenseResponse.Companion.toResponse
import top.oatnil.undercontrol.expense.domain.Expense
import java.math.BigDecimal
import java.time.LocalDate

data class DailyExpenses(
    val total: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val date: LocalDate,
    val expenses: List<ExpenseResponse>
) {
    companion object {
        fun from(expenses: Collection<Expense>, date: LocalDate): DailyExpenses {
            return DailyExpenses(
                total = expenses.sumOf { it.amount },
                date = date,
                expenses = expenses
                    .map { it.toResponse() }
                    .sortedByDescending { it.time }
            )
        }
    }
}
