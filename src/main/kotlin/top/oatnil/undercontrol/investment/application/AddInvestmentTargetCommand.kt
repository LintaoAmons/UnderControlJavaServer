package top.oatnil.undercontrol.investment.application

import top.oatnil.undercontrol.investment.domain.target.Fund
import top.oatnil.undercontrol.investment.domain.target.Tag
import java.math.BigDecimal
import java.util.*

data class AddInvestmentTargetCommand(
    val name: String,
    val code: Int,
    val tags: List<Tag>,
    val holdingCount: Long, // 持仓数
    val averageBuyInPrice: BigDecimal, // 买入均价
    val costPerUint: BigDecimal, // 成本
) {
    fun toInvestmentTarget(): Fund {
        return Fund(
            id = UUID.randomUUID().toString(),
            name = name,
            code = code,
            tags = tags,
            holdingCount = holdingCount,
            averageBuyInPrice = averageBuyInPrice,
            costPerUint = costPerUint,
            markingPrice = null
        )
    }
}