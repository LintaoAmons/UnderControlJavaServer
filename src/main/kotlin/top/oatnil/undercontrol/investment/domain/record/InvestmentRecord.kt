package top.oatnil.undercontrol.investment.domain.record

import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal
import java.time.LocalDateTime

data class InvestmentRecord(
    @MongoId
    val id: String,
    val buyInUnitPrice: BigDecimal,
    val quantity: Long,
    val investmentTargetId: String,
    val createdAt: LocalDateTime,
)

interface InvestmentRecordRepository {
    fun save(investmentRecord: InvestmentRecord)
    fun findAll(): List<InvestmentRecord>
}
