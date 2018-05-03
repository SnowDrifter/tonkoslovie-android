package ru.romanov.tonkoslovie.data.models.user

data class UserResponse(
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String
)