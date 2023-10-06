package top.oatnil.undercontrol.expense

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import top.oatnil.undercontrol.UndercontrolApplication


@SpringBootTest(classes = [UndercontrolApplication::class])
@AutoConfigureMockMvc
class ExpenseIntegrationTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc // 奇怪， IDEA 报错，但是实际上是找到了的

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `test expense homepage`() {
        mockMvc.get("/expense/home-page?referenceId=lintao&month=JUNE") {
            header("Authorization", "Bearer c9113d5f-03dd-4407-94de-4b4f2462ca0a-its-me-lintao")
        }
            .andExpect {
                status {
                    isOk()
                }
                content {
                    string(
                        "{\"totalExpense\":245.20,\"dailyExpenses\":[{\"total\":101.25,\"date\":[2022,6,30],\"expenses\":[{\"id\":\"34fe0adb-fc64-4b61-b100-f7b97f0cc230\",\"category\":\"food\",\"amount\":77,\"date\":\"2022-06-30\",\"time\":\"17:34:40\",\"note\":\"\"},{\"id\":\"70bffad7-027d-4119-8d25-b0e0f271ec4a\",\"category\":\"food\",\"amount\":19.75,\"date\":\"2022-06-30\",\"time\":\"11:49:47\",\"note\":\"\"},{\"id\":\"320d22dc-4603-46d3-8de1-769db1a7b7af\",\"category\":\"food\",\"amount\":4.5,\"date\":\"2022-06-30\",\"time\":\"09:36:15\",\"note\":\"\"}]},{\"total\":129.05,\"date\":[2022,6,29],\"expenses\":[{\"id\":\"7e8c114f-e19b-4954-8786-8783e3befbaf\",\"category\":\"food\",\"amount\":88.8,\"date\":\"2022-06-29\",\"time\":\"19:46:02\",\"note\":\"\"},{\"id\":\"54ca144f-3c47-4ce9-a2d6-3f436a5e7168\",\"category\":\"food\",\"amount\":17.5,\"date\":\"2022-06-29\",\"time\":\"18:28:54\",\"note\":\"\"},{\"id\":\"d8bacb27-469e-4ee8-acaf-a795c2bea4ee\",\"category\":\"food\",\"amount\":22.75,\"date\":\"2022-06-29\",\"time\":\"12:10:10\",\"note\":\"\"}]},{\"total\":14.9,\"date\":[2022,6,28],\"expenses\":[{\"id\":\"99522a1d-87de-4a99-b904-bc88ea0538c0\",\"category\":\"food\",\"amount\":14.9,\"date\":\"2022-06-28\",\"time\":\"22:36:57\",\"note\":\"æ\u0099\u009Aé¥\u00AD\"}]}]}"
                    )
                }
            }
    }
}