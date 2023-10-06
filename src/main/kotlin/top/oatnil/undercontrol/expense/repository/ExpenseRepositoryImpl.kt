package top.oatnil.undercontrol.expense.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import top.oatnil.undercontrol.expense.domain.Expense
import top.oatnil.undercontrol.expense.domain.ExpenseException
import top.oatnil.undercontrol.expense.domain.ExpenseRepository
import java.time.LocalDateTime
import java.time.Month
import java.time.Year

@Repository
class ExpenseRepositoryImpl(
    val expenseMongoRepository: ExpenseMongoRepository
) : ExpenseRepository {
    override fun save(domain: Expense) {
        expenseMongoRepository.save(domain)
    }

    override fun save(domains: List<Expense>) {
        expenseMongoRepository.saveAll(domains)
    }

    override fun findById(id: String): Expense? {
        return expenseMongoRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<Expense> {
        return expenseMongoRepository.findAll()
    }

    override fun findAllByIds(ids: List<String>): List<Expense> {
        TODO("Not yet implemented")
    }

    override fun findAllByUserId(userId: String): List<Expense> {
        return expenseMongoRepository.findAllByDomainFields_UserId(userId)
    }

    override fun get(id: String): Expense {
        return expenseMongoRepository.findByIdOrNull(id) ?: throw ExpenseException("Can't find expense by id: $id")
    }

    override fun findAllByUserIdAndMonth(userId: String, month: Month): List<Expense> {
        return expenseMongoRepository.findAllByDomainFields_UserIdAndDomainFields_CreatedAtBetween(
            userId,
            month.startDateTime(),
            month.endDateTime()
        )
    }

    override fun delete(id: String) {
        expenseMongoRepository.deleteById(id)
    }

}

interface ExpenseMongoRepository : MongoRepository<Expense, String> {
    fun findAllByDomainFields_UserId(referenceId: String): List<Expense>
    fun findAllByDomainFields_UserIdAndDomainFields_CreatedAtBetween(
        referenceId: String,
        from: LocalDateTime,
        to: LocalDateTime
    ): List<Expense>
}

fun Month.startDateTime(): LocalDateTime = Year.now().atMonth(this).atDay(1).atStartOfDay()
fun Month.endDateTime(): LocalDateTime = this.startDateTime().plusMonths(1)
