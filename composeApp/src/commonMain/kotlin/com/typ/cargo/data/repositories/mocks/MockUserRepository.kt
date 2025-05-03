package com.typ.cargo.data.repositories.mocks

import com.typ.cargo.data.models.User
import com.typ.cargo.data.repositories.abstractions.UserRepository
import com.typ.cargo.enums.UserRole

class MockUserRepository : UserRepository {

    override suspend fun getCurrentUser(): User {
        return User(
            id = "typ",
            name = "Ahmed Sleem",
            role = UserRole.ADMIN,
            username = "typahmedsleem",
            photoPainterId = "picAhmedSleem"
        )
    }

    override suspend fun loginUser(username: String, password: String): Result<User> {
        return if (username == "typahmedsleem" && password == "ahmed.sleem") {
            Result.success(getCurrentUser())
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}