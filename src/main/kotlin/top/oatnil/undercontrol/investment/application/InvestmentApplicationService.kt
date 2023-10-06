package top.oatnil.undercontrol.investment.application

import org.springframework.stereotype.Service
import top.oatnil.undercontrol.investment.domain.record.InvestmentRecord
import top.oatnil.undercontrol.investment.domain.record.InvestmentRecordRepository
import top.oatnil.undercontrol.investment.domain.exception.InvestmentException
import top.oatnil.undercontrol.investment.domain.target.Fund
import top.oatnil.undercontrol.investment.domain.target.InvestmentTargetRepository

@Service
class InvestmentApplicationService(
    val investmentTargetRepository: InvestmentTargetRepository,
    val investmentRecordRepository: InvestmentRecordRepository
) {
    fun addInvestmentTarget(request: AddInvestmentTargetCommand) {
        investmentTargetRepository.save(request.toInvestmentTarget())
    }

    fun getInvestmentTarget(id: String): Fund {
        return investmentTargetRepository.findById(id) ?: TODO()
    }

    fun findAllTargets(): List<Fund> {
        return investmentTargetRepository.findAll()
    }

    fun invest(investCommand: InvestCommand) {
        val id = investCommand.investmentTargetId
        investmentTargetRepository.findById(id)
            ?: throw InvestmentException("No investmentTarget found by Id [${id}]")
        investmentRecordRepository.save(investCommand.toInvestmentRecord())
    }

    fun showAllInvestment(): List<InvestmentRecord> {
        return investmentRecordRepository.findAll()
    }
}