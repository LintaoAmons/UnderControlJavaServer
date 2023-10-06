package top.oatnil.undercontrol.expense.domain

import org.springframework.data.mongodb.core.mapping.MongoId
import top.oatnil.undercontrol.base.DomainFields
import top.oatnil.undercontrol.base.DomainObject
import top.oatnil.undercontrol.base.DomainRepository
import java.math.BigDecimal
import java.time.Month

data class Expense(
    @MongoId
    override val id: String,
    val category: Category,
    val amount: BigDecimal,
    val note: String,

    override val domainFields: DomainFields,
) : DomainObject

interface ExpenseRepository : DomainRepository<Expense> {
    fun findAllByUserIdAndMonth(userId: String, month: Month): List<Expense>
}