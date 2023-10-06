package top.oatnil.undercontrol.account.infra

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import top.oatnil.undercontrol.account.domain.ChangeRecord
import top.oatnil.undercontrol.account.domain.ChangeRecordRepository

@Repository
class ChangeRecordRepositoryImpl(val mongoRepository: ChangeRecordMongoRepository) : ChangeRecordRepository {
    override fun save(changeRecord: ChangeRecord): String {
        return mongoRepository.save(changeRecord).id
    }

    override fun findById(id: String): ChangeRecord? {
        return mongoRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<ChangeRecord> {
        return mongoRepository.findAll()
    }

    override fun findByAccountId(accountId: String): List<ChangeRecord> {
        return mongoRepository.findAllByAccountId(accountId)
    }
}

interface ChangeRecordMongoRepository : MongoRepository<ChangeRecord, String> {
    fun findAllByAccountId(accountId: String): List<ChangeRecord>
}