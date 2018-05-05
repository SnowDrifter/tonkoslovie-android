package ru.romanov.tonkoslovie

import android.app.Application
import io.paperdb.Paper
import ru.romanov.tonkoslovie.dependencies.ApplicationComponent
import ru.romanov.tonkoslovie.dependencies.DaggerApplicationComponent
import ru.romanov.tonkoslovie.dependencies.modules.ApiModule

class TonkoslovieApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .apiModule(ApiModule())
                .build()
    }

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