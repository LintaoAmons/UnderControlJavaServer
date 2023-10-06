package top.oatnil.undercontrol.account.domain

import top.oatnil.undercontrol.base.DomainFields
import java.math.BigDecimal
import java.util.*

data class AccountTransferCommand(
    val transferType: TransferType,
    val currentAccountId: String,
    val targetAccountId: String,
    val amount: BigDecimal,
    val note: String?,
) {
    fun accountIds() = listOf(currentAccountId, targetAccountId)

    fun toTransRecord(): TransferRecord {
        return TransferRecord(
            id = UUID.randomUUID().toString(),
            transferType = transferType,
            currentAccountId = currentAccountId,
            targetAccountId = targetAccountId,
            amount = amount,
            note = note ?: "",
            domainFields = DomainFields.init(),
        )
    }
}
