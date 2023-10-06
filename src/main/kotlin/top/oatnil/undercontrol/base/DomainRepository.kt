package top.oatnil.undercontrol.base


interface DomainRepository<T : DomainObject> {
    fun save(domain: T)
    fun save(domains: List<T>)

    fun findById(id: String): T?
    fun findAll(): List<T>
    fun findAllByIds(ids: List<String>): List<T>
    fun findAllByUserId(userId: String): List<T>

    fun get(id: String): T

    fun delete(id: String)
}