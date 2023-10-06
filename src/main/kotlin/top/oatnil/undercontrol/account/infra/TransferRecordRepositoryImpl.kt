package top.oatnil.undercontrol.account.infra

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import top.oatnil.undercontrol.account.domain.TransferRecord
import top.oatnil.undercontrol.account.domain.TransferRecordRepository

@Repository
class TransferRecordRepositoryImpl(
    private val mongoRepository: TransferRecordMongoRepository
) : TransferRecordRepository {
    override fun getById(id: String): TransferRecord {
        return mongoRepository.findByIdOrNull(id)
            ?: throw RuntimeException("Can't find TransferRecord by id: $id")
    }

    override fun findById(id: String): TransferRecord? {
        return mongoRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<TransferRecord> {
        return mongoRepository.findAll()
    }

    override fun save(domain: TransferRecord): String {
        return mongoRepository.save(domain).id
    }
}

interface TransferRecordMongoRepository : MongoRepository<TransferRecord, String>
