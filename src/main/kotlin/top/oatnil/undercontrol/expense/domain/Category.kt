package top.oatnil.undercontrol.expense.domain

data class Category(val value: String) {
    companion object {
        fun fromString(value: String): Category {
            // TODO: 2022/6/30 Add validation
            return Category(value)
        }
    }
}
