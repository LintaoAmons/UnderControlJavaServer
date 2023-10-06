package top.oatnil.undercontrol.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import top.oatnil.undercontrol.config.interceptor.ItsMeInterceptor

@Configuration
class MyWebMvcConfigurer(val itsMeInterceptor: ItsMeInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(itsMeInterceptor).addPathPatterns("/**")
    }

}