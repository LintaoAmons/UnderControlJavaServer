package top.oatnil.undercontrol.common


class UserContext private constructor() : AutoCloseable {

    companion object {
        private val ctx = ThreadLocal<String>()

        fun init(userId: String) {
            ctx.set(userId)
        }

        fun getCurrentUser(): String {
            return ctx.get()
        }
    }

    override fun close() {
        ctx.remove()
    }

}