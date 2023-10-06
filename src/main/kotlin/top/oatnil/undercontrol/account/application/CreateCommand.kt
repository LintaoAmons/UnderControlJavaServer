package top.oatnil.undercontrol.account.application

import top.oatnil.undercontrol.account.domain.Account
import top.oatnil.undercontrol.base.DomainFields
import java.math.BigDecimal
import java.util.*

data class CreateCommand(
    val amount: BigDecimal,
    val name: String,
    val number: String,
    val note: String?,
) {
    fun toAccount(): Account {
        return Account(
            id = UUID.randomUUID().toString(),
            name = name,
            number = number,
            amount = amount,
            note = note ?: "",

            domainFields = DomainFields.init(),
        )
    }
}
