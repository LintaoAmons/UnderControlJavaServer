package top.oatnil.undercontrol.expense.application

import top.oatnil.undercontrol.base.DomainFields
import top.oatnil.undercontrol.expense.domain.Category
import top.oatnil.undercontrol.expense.domain.Expense
import java.math.BigDecimal
import java.util.*

data class CreateExpenseCommand(
    val referenceId: String,
    val category: String,
    val amount: BigDecimal,
    val note: String?,
) {
    fun toExpense(): Expense {
        return Expense(
            id = UUID.randomUUID().toString(),
            category = Category(category),
            amount = amount,
            note = note ?: "",

            domainFields = DomainFields.init(),
        )
    }
}
