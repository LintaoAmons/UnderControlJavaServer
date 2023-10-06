package top.oatnil.undercontrol.investment.domain.target

import org.springframework.data.mongodb.core.mapping.MongoId
import top.oatnil.undercontrol.investment.domain.Utils.multiply
import java.math.BigDecimal

data class Fund(
    @MongoId
    val id: String,
    val name: String,
    val code: Int,
    val tags: List<Tag>,
    val holdingCount: Long, // 持仓数
    val averageBuyInPrice: BigDecimal, // 买入均价
    val costPerUint: BigDecimal, // 成本
    var markingPrice: BigDecimal?, // 市价
) {
    fun getMarketValue(): BigDecimal { // 市值
        return markingPrice.multiply(holdingCount)
    }
}


interface InvestmentTargetRepository {
    fun save(fund: Fund)
    fun findById(id: String): Fund?
    fun findAll(): List<Fund>
}