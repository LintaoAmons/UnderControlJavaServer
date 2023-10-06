package top.oatnil.undercontrol.account

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import top.oatnil.undercontrol.UndercontrolApplication
import top.oatnil.undercontrol.account.domain.AccountTransferCommand
import top.oatnil.undercontrol.account.domain.TransferType
import java.math.BigDecimal


@SpringBootTest(classes = [UndercontrolApplication::class])
@AutoConfigureMockMvc
class AccountIntegrationTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc // 奇怪， IDEA 报错，但是实际上是找到了的

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `test findAvailableAccounts`() {
        mockMvc.get("/accounts/availableAccounts?excludeAccountIds=") {
            header("Authorization", "Bearer c9113d5f-03dd-4407-94de-4b4f2462ca0a-its-me-lintao")
        }
            .andExpect {
                status {
                    isOk()
                }
                content {
                    string(
                        "[{\"id\":\"6813af45-503b-40da-b94d-62222187b1d2\",\"name\":\"TestAccount\"},{\"id\":\"3a1c80eb-dbf0-410a-8b96-2a65940b444a\",\"name\":\"ChinaBank\"}]"
                    )
                }
            }
    }

    @Test
    fun transfer() {
        mockMvc.post("/accounts/transfer") {
            header("Authorization", "Bearer c9113d5f-03dd-4407-94de-4b4f2462ca0a-its-me-lintao")
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                AccountTransferCommand(
                    transferType = TransferType.IN,
                    "72aceb07-b58c-491b-a19c-59b188ed4e78",
                    "6813af45-503b-40da-b94d-62222187b1d2",
                    BigDecimal.TEN,
                    null
                )
            )
        }
            .andExpect {
                status {
                    isOk()
                }
            }
    }

    // TODO: 2022/9/5 Add deposit and create test
}