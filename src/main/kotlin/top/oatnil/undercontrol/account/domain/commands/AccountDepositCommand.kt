package top.oatnil.undercontrol.account.domain.commands

import java.math.BigDecimal

data class AccountDepositCommand(
    val accountId: String,
    val amount: BigDecimal
)
