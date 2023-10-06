package top.oatnil.undercontrol.investment.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import top.oatnil.undercontrol.investment.domain.record.InvestmentRecord
import top.oatnil.undercontrol.investment.domain.record.InvestmentRecordRepository

@Repository
class InvestmentRecordRepositoryImpl(val mongoRepository: InvestmentRecordMongoRepository) :
    InvestmentRecordRepository {
    override fun save(investmentRecord: InvestmentRecord) {
        mongoRepository.save(investmentRecord)
    }

    override fun findAll(): List<InvestmentRecord> {
        return mongoRepository.findAll()
    }

}


interface InvestmentRecordMongoRepository : MongoRepository<InvestmentRecord, String>