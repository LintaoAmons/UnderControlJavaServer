package top.oatnil.undercontrol.expense.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import top.oatnil.undercontrol.expense.domain.Expense
import java.math.BigDecimal
import java.time.LocalDateTime

data class ExpenseResponse(
    val id: String,
    val category: String,
    val amount: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val date: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    val time: LocalDateTime,
    val note: String,
) {
    companion object {
        fun Expense.toResponse(): ExpenseResponse {
            return ExpenseResponse(
                id = id,
                category = category.value,
                amount = amount,
                date = domainFields.createdAt,
                time = domainFields.createdAt,
                note = note
            )
        }
    }
}