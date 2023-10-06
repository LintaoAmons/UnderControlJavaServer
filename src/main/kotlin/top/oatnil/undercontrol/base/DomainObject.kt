package top.oatnil.undercontrol.base

import top.oatnil.undercontrol.common.UserContext
import java.time.LocalDateTime

data class DomainFields(
    val userId: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun init(): DomainFields {
            val now = LocalDateTime.now()
            return DomainFields(
                userId = UserContext.getCurrentUser(),
                createdAt = now,
                updatedAt = now,
            )
        }
    }

    fun checkUser() {
        if (userId != UserContext.getCurrentUser()) {
            throw RuntimeException("Unable to perform this action")
        }
    }

    fun update(): DomainFields {
        return this.copy(updatedAt = LocalDateTime.now())
    }
}

interface DomainObject {
    val id: String
    val domainFields: DomainFields

    fun updateDomainFields(): DomainFields {
        return domainFields.update()
    }
}
