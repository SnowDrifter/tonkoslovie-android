package ru.romanov.tonkoslovie

import android.app.Application

class TonkoslovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TonkoslovieApplication
            private set
    }

}