package top.oatnil.undercontrol.investment.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDateTime

data class InvestmentHomePageResponse(
    val aggregateMarketValue: BigDecimal,
    val aggregateCost: BigDecimal,
    val aggregateCostOfThisMonth: BigDecimal,
    val investmentTargets: List<InvestmentTargetResponse>,
)

data class InvestmentTargetResponse(
    val name: String,
    val code: Int,
    val markingPrice: MarkingPrice,
    val costPerUnit: BigDecimal,
    val holdingCount: Long,
    val averageBuyInPrice: BigDecimal,
    val marketValue: BigDecimal,
)

data class MarkingPrice(
    val price: BigDecimal,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime
)
