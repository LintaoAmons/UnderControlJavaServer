package top.oatnil.undercontrol.account.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import top.oatnil.undercontrol.account.domain.Account
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountResponse(
    val id: String,
    var amount: BigDecimal,
    val name: String,
    val number: String,
    val note: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime
) {
    companion object {
        fun Account.toResponse(): AccountResponse {
            return AccountResponse(
                id = id,
                amount = amount,
                name = name,
                number = number,
                note = note,
                updatedAt = domainFields.updatedAt
            )
        }
    }
}