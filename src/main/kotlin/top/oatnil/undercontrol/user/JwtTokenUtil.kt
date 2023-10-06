package top.oatnil.undercontrol.user

import io.jsonwebtoken.*
import org.springframework.stereotype.Component
import top.oatnil.undercontrol.common.logger
import java.util.*

@Component
class JwtTokenUtil {
    private val jwtSecret: String =
        "VpfHQrCqPGgr1Hi3O7PyjmPBC2HJFSakCwgJX7DPQ4Qnmq46YhhtJLb8MwKRrV0aK9zKEhHe7MuC5AxDZQ6lUz5uMW3lqMZW5Z2VEknELmVdNCSCLAw1uZBqERZhAX5t"
    private val jwtIssuer: String = "top.oatnil"
    val logger = logger()

    fun generateAccessToken(user: User): String {
        return Jwts.builder()
            .setIssuer(jwtIssuer)
            .setClaims(mutableMapOf("username" to user.username) as Map<String, Any>?)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUsername(token: String): String {
        val claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body

        return claims["username"].toString()
    }

    fun validate(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature - {}", ex.message)
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token - {}", ex.message)
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token - {}", ex.message)
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token - {}", ex.message)
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty - {}", ex.message)
        }
        return false
    }
}
