package top.oatnil.undercontrol.account.domain

import java.math.BigDecimal

class AccountTransferCommandFixture {
    companion object {
        val accountTransferCommand = AccountTransferCommand(
            transferType = TransferType.IN,
            currentAccountId = "72aceb07-b58c-491b-a19c-59b188ed4e78",
            targetAccountId = "6813af45-503b-40da-b94d-62222187b1d2",
            amount = BigDecimal.TEN,
            note = null,
        )
    }
}