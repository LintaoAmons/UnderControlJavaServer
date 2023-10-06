package top.oatnil.undercontrol.config.interceptor

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import top.oatnil.undercontrol.common.UserContext
import top.oatnil.undercontrol.common.logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

val PUBLIC_URLS = listOf(
    "/v3/api-docs",
)

@Component
class ItsMeInterceptor : HandlerInterceptor {
    val logger = logger()

    @Value("\${undercontrol.its-me}")
    lateinit var token: String

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // TODO HttpServletRequest 的具体实现是什么类
        if (request.requestURI in PUBLIC_URLS) {
            return true
        }

        val token = request.getHeader("Authorization")?.substring(7)
        if (token == this.token) {
            UserContext.init("lintao")
            return true
        }
        UserContext.init("anonymous")
        logger.info("My token is: ", this.token)
        logger.info("Not me: {}", token)
        return true
    }

}