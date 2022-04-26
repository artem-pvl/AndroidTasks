package com.example.task17

data class PhoneRecord(
    val name: String,
    val phone: String,
    val type: String,
) {
    fun hasSubSting(str: String): Boolean {
        return when {
            name.contains(str, ignoreCase = true) -> true
            phone.contains(str, ignoreCase = true) -> true
            type.contains(str, ignoreCase = true) -> true
            else -> false
        }
    }
}
