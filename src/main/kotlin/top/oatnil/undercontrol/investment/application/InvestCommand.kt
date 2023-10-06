package top.oatnil.undercontrol.investment.application

import top.oatnil.undercontrol.investment.domain.record.InvestmentRecord
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class InvestCommand(
    val buyInUnitPrice: BigDecimal,
    val quantity: Long,
    val investmentTargetId: String,
) {
    fun toInvestmentRecord(): InvestmentRecord {
        return InvestmentRecord(UUID.randomUUID().toString(), buyInUnitPrice, quantity, investmentTargetId, LocalDateTime.now())
    }
}
