package top.oatnil.undercontrol.investment.domain.Utils

import java.math.BigDecimal

fun BigDecimal?.multiply(longValue: Long): BigDecimal {
    return this?.multiply(BigDecimal.valueOf(longValue))
        ?: BigDecimal.ZERO
}
