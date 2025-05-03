package com.typ.cargo.data.repositories.abstractions

import com.typ.cargo.data.models.User

interface UserRepository {

    suspend fun getCurrentUser() : User

    suspend fun loginUser(username: String, password: String) : Result<User>

}