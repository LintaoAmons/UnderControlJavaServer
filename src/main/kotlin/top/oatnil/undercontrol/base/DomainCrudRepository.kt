package top.oatnil.undercontrol.base


interface DomainCrudRepository<T : DomainObject> {
    fun getById(id: String): T
    fun findById(id: String): T?
    fun findAll(): List<T>

    fun save(domain: T): String
}