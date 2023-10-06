package top.oatnil.undercontrol.user

import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService,
    val jwtTokenUtil: JwtTokenUtil
) {
    @PostMapping("/my-login")
    fun login(@RequestBody loginRequest: LoginRequest): String {
        val (username, passwd) = loginRequest
        val user = User(username, passwd)
        val userDetail = userService.loadUserByUsername(username)
        if (user.password == userDetail.password) {
            return jwtTokenUtil.generateAccessToken(user)
        } else {
            throw RuntimeException("Login failed")
        }
    }

    @GetMapping("/userInfo")
    fun getUserInfo(@RequestParam token: String): UserInfoResponse {
        if (!jwtTokenUtil.validate(token)) {
            throw RuntimeException("1111111111")
        }

        val userDetails = userService.loadUserByUsername(jwtTokenUtil.getUsername(token))

        return UserInfoResponse(userDetails.username, emptyList())
    }
}

data class LoginRequest(
    val username: String,
    val password: String,
)

data class UserInfoResponse(
    val username: String,
    val roles: List<String>,
)

data class User(
    val username: String,
    val password: String,
)