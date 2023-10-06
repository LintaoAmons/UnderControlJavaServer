package top.oatnil.undercontrol.config.interceptor

import org.junit.jupiter.api.Test

internal class ItsMeInterceptorUnitTest {
    @Test
    fun `extract token from header`() {
        val headerValue = "Bearer I myself is using this api"

        val result = headerValue.substring(7)

        println(result)
    }
}