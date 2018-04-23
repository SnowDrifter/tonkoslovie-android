package ru.romanov.tonkoslovie.utils

import ru.romanov.tonkoslovie.TonkoslovieApplication

object ResourceUtils {

    fun getString(stringId: Int): String {
        val instance = TonkoslovieApplication.instance
        return instance.resources.getString(stringId)
    }

}