package top.oatnil.undercontrol.expense.application

import top.oatnil.undercontrol.expense.domain.Category
import top.oatnil.undercontrol.expense.domain.Expense
import java.math.BigDecimal
import java.time.LocalDateTime

data class UpdateExpenseCommand(
    val category: String?,
    val amount: BigDecimal?,
    val createdAt: LocalDateTime?,
    val note: String?,
) {
    fun toExpense(current: Expense): Expense {
        return current.copy(
            category = category?.let { Category.fromString(it) }
                ?: current.category,
            amount = amount ?: current.amount,
            note = note ?: current.note,

            domainFields = current.updateDomainFields(),
        )
    }

}
