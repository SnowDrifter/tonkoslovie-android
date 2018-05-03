package ru.romanov.tonkoslovie

import android.app.Application
import io.paperdb.Paper

class TonkoslovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        instance = this
    }

    companion object {
        lateinit var instance: TonkoslovieApplication
            private set
    }

}