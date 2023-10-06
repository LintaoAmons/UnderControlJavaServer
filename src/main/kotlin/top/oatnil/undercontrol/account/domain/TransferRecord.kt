package top.oatnil.undercontrol.account.domain

import top.oatnil.undercontrol.base.DomainCrudRepository
import top.oatnil.undercontrol.base.DomainFields
import top.oatnil.undercontrol.base.DomainObject
import java.math.BigDecimal

// record the transfer changes to the account
data class TransferRecord(
    override val id: String,
    val transferType: TransferType,
    val currentAccountId: String,
    val targetAccountId: String,
    val amount: BigDecimal,
    val note: String,
    override val domainFields: DomainFields
) : DomainObject

enum class TransferType {
    IN,
    OUT
}

interface TransferRecordRepository : DomainCrudRepository<TransferRecord>
