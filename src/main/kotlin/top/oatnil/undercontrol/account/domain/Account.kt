package top.oatnil.undercontrol.account.domain

import org.springframework.data.mongodb.core.mapping.MongoId
import top.oatnil.undercontrol.account.domain.exception.TransferException
import top.oatnil.undercontrol.base.DomainFields
import top.oatnil.undercontrol.base.DomainObject
import java.math.BigDecimal

data class Account(
    @MongoId
    override val id: String,
    var amount: BigDecimal,
    val name: String,
    val number: String,
    val note: String,

    override val domainFields: DomainFields
) : DomainObject {
    companion object {
    }

    fun deposit(amount: BigDecimal) {
        this.amount = this.amount + amount
    }

    fun withdraw(amount: BigDecimal) {
        val remainAmount = this.amount - amount
        if (remainAmount < BigDecimal.ZERO) {
            throw TransferException("Insufficient amount of $name: ${this.amount}, but need $amount")
        }
        this.amount = remainAmount
    }

}

interface AccountRepository {
    fun save(account: Account): String
    fun save(accounts: List<Account>)

    fun findById(id: String): Account?
    fun findAll(): List<Account>
    fun findAllByIds(ids: List<String>): List<Account>
    fun findAllByUserId(userId: String): List<Account>

    fun getById(id: String): Account
}