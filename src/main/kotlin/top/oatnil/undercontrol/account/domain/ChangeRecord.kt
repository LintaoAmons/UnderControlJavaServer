package top.oatnil.undercontrol.account.domain

import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal
import java.time.LocalDateTime

// record the directly changes to the account
class ChangeRecord(
    @MongoId
    val id: String,
    val accountId: String,
    val amount: BigDecimal?,
    val name: String?,
    val number: String?,
    val reason: String?,
    val createdBy: String,
    val createdDate: LocalDateTime,
)

interface ChangeRecordRepository {
    fun save(changeRecord: ChangeRecord): String
    fun findById(id: String): ChangeRecord?
    fun findAll(): List<ChangeRecord>
    fun findByAccountId(accountId: String): List<ChangeRecord>
}
