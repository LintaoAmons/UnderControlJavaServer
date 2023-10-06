package top.oatnil.undercontrol.base

import top.oatnil.undercontrol.common.UserContext

class DomainResourceUtil private constructor() {
    companion object {
        fun checkOwnership(domainObject: DomainObject) {
            if (domainObject.domainFields.userId != UserContext.getCurrentUser()) {
                throw RuntimeException("Can't access this resource")
            }
        }

        fun checkOwnership(domainObjects: Collection<DomainObject>) {
            domainObjects.forEach { checkOwnership(it) }
        }
    }
}