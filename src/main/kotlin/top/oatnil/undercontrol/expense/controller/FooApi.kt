package top.oatnil.undercontrol.expense.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/foo")
class FooApi {
    @GetMapping("/{id}")
    fun getFoo(@PathVariable id: String): String {
        println(id)
        return id
    }
}
