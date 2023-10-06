package top.oatnil.undercontrol.account.infra

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import top.oatnil.undercontrol.account.domain.Account
import top.oatnil.undercontrol.account.domain.AccountRepository

@Repository
class AccountRepositoryImpl(val mongoRepository: AccountMongoRepository) : AccountRepository {
    override fun save(account: Account): String {
        return mongoRepository.save(account.copy(domainFields = account.updateDomainFields())).id
    }

    override fun save(accounts: List<Account>) {
        mongoRepository.saveAll(accounts.map { it.copy(domainFields = it.updateDomainFields()) })
    }

    override fun findById(id: String): Account? {
        return mongoRepository.findByIdOrNull(id);
    }

    override fun findAll(): List<Account> {
        return mongoRepository.findAll();
    }

    override fun findAllByIds(ids: List<String>): List<Account> {
        return mongoRepository.findAllById(ids).toList()
    }

    override fun findAllByUserId(userId: String): List<Account> {
        return mongoRepository.findAllByDomainFields_UserId(userId)
    }

    override fun getById(id: String): Account {
        return findById(id) ?: throw RuntimeException("Can't find Account by id: ${id}")
    }

}

interface AccountMongoRepository : MongoRepository<Account, String> {
    fun findAllByDomainFields_UserId(userId: String): List<Account>
}