package top.oatnil.undercontrol.user

import org.springframework.stereotype.Component
import top.oatnil.undercontrol.user.User

@Component
class UserService {

    fun loadUserByUsername(username: String?): User {
        return User("lintao", "passwd")
    }
}