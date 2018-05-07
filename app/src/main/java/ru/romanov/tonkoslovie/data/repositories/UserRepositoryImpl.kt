package ru.romanov.tonkoslovie.data.repositories

import io.paperdb.Paper
import ru.romanov.tonkoslovie.utils.Constants

class UserRepositoryImpl : UserRepository {

    override fun saveToken(token: String) {
        Paper.book(Constants.PaperBook.USER).write(Constants.PaperKey.TOKEN, token)
    }

    override fun getToken(): String {
        return Paper.book(Constants.PaperBook.USER).read(Constants.PaperKey.TOKEN) ?: ""
    }

    override fun deleteToken() {
        Paper.book(Constants.PaperBook.USER).delete(Constants.PaperKey.TOKEN)
    }

}