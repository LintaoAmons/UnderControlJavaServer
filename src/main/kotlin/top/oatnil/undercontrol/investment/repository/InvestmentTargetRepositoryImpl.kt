package top.oatnil.undercontrol.investment.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import top.oatnil.undercontrol.investment.domain.target.Fund
import top.oatnil.undercontrol.investment.domain.target.InvestmentTargetRepository

interface InvestmentTargetMongoRepository : MongoRepository<Fund, String>

@Repository
class InvestmentTargetRepositoryImpl(val mongoRepository: InvestmentTargetMongoRepository) :
    InvestmentTargetRepository {
    override fun save(fund: Fund) {
        mongoRepository.save(fund)
    }

    override fun findById(id: String): Fund? {
        return mongoRepository.findByIdOrNull(id)
    }

    override fun findAll(): List<Fund> {
        return mongoRepository.findAll()
    }

}