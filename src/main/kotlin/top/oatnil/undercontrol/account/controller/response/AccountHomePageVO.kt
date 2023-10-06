package top.oatnil.undercontrol.account.controller.response

import java.math.BigDecimal

data class AccountHomePageVO(
    val totalAmount: BigDecimal,
    val accounts: List<AccountResponse>
)