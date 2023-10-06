package top.oatnil.undercontrol.account.domain

import top.oatnil.undercontrol.base.domainFieldsFixture
import java.math.BigDecimal

val accountFixture = Account(
    id = "897a05c3-a081-4bc2-acdd-28b572a14ead",
    amount = BigDecimal.TEN,
    name = "中国银行",
    number = "1234 1234 1234",
    note = "",
    domainFields = domainFieldsFixture,
)
