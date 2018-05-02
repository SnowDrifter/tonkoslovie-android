package ru.romanov.tonkoslovie.data.repositories

interface UserRepository {

    fun saveToken(token: String)

    fun getToken(): String

    fun deleteToken()

}