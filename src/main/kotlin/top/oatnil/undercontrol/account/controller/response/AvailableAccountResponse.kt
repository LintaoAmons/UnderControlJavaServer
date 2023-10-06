package top.oatnil.undercontrol.account.controller.response

import top.oatnil.undercontrol.account.domain.Account

data class AvailableAccountResponse(
    val id: String,
    val name: String
) {
    companion object {
        fun Account.toAvailableAccountResponse(): AvailableAccountResponse {
            return AvailableAccountResponse(id = id, name = name)
        }
    }
}
