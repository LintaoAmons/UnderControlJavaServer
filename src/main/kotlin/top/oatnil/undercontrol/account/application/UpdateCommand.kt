package top.oatnil.undercontrol.account.application

import top.oatnil.undercontrol.account.domain.Account
import top.oatnil.undercontrol.account.domain.ChangeRecord
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class UpdateCommand(
    val amount: BigDecimal?,
    val name: String?,
    val number: String?,
    val reason: String?,
) {
    fun toAccount(old: Account): Account {
        return old.copy(
            amount = amount ?: old.amount,
            name = name ?: old.name,
            number = number ?: old.number
        )
    }

    fun toChangeRecord(accountId: String): ChangeRecord {
        return ChangeRecord(
            id = UUID.randomUUID().toString(),
            accountId = accountId,
            amount = amount,
            name = name,
            number = number,
            reason = reason,
            createdBy = "Lintao", // TODO: put createdBy into requestHeader
            createdDate = LocalDateTime.now()
        )
    }

}