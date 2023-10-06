package top.oatnil.undercontrol.investment.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.web.bind.annotation.*
import top.oatnil.undercontrol.investment.application.AddInvestmentTargetCommand
import top.oatnil.undercontrol.investment.application.InvestCommand
import top.oatnil.undercontrol.investment.application.InvestmentApplicationService
import top.oatnil.undercontrol.investment.controller.response.InvestmentHomePageResponse
import top.oatnil.undercontrol.investment.domain.record.InvestmentRecord
import top.oatnil.undercontrol.investment.domain.target.Fund

@RestController
@RequestMapping("/investment")
class InvestmentController(
    val investmentApplicationService: InvestmentApplicationService,
    val objectMapper: ObjectMapper
) {

    @PostMapping("/targets")
    fun addInvestmentTarget(@RequestBody request: AddInvestmentTargetCommand) =
        investmentApplicationService.addInvestmentTarget(request)

    @GetMapping("/target/{id}")
    fun getById(@PathVariable id: String): Fund {
        return investmentApplicationService.getInvestmentTarget(id)
    }

    @GetMapping("/targets")
    fun findAll(): List<Fund> {
        return investmentApplicationService.findAllTargets()
    }

    @PostMapping("/records")
    fun invest(@RequestBody request: InvestCommand) {
        return investmentApplicationService.invest(request)
    }

    @GetMapping("/records")
    fun showAllInvestment(): List<InvestmentRecord> {
        return investmentApplicationService.showAllInvestment()
    }

    @GetMapping("/home")
    fun investmentHome(): InvestmentHomePageResponse {
        val mockResponse = """
            {
              "aggregateMarketValue": 10002.72,
              "aggregateCost": 7000.2,
              "aggregateCostOfThisMonth": 3000.12,
              "investmentTargets": [
                {
                  "name": "500ETF",
                  "code": 500500,
                  "markingPrice": {
                    "price": 1.191,
                    "updatedAt": "2020-01-03 04:20:03"
                  },
                  "costPerUnit": 0.696,
                  "holdingCount": 7800,
                  "averageBuyInPrice": 0.756,
                  "marketValue": 9408.9
                },
                {
                  "name": "700ETF",
                  "code": 700700,
                  "markingPrice": {
                    "price": 2.191,
                    "updatedAt": "2020-09-09 04:20:03"
                  },
                  "costPerUnit": 0.822,
                  "holdingCount": 9300,
                  "averageBuyInPrice": 0.259,
                  "marketValue": 13408.3
                }
              ]
            }
        """.trimIndent()
        return objectMapper.readValue(mockResponse)
    }

}