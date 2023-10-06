package top.oatnil.undercontrol.account.controller.response

import top.oatnil.undercontrol.account.domain.ChangeRecord

data class AccountDetailsResponse(
    val account: AccountResponse,
    val changeRecords: List<ChangeRecord>
)