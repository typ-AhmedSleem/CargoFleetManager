package com.typ.cargo.data.models

import com.typ.cargo.enums.UserRole

data class User(
    val id: String,
    val name: String,
    val username: String,
    val role: UserRole,
    val photoPainterId: String? = null
)
