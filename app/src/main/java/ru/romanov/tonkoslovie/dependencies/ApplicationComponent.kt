package ru.romanov.tonkoslovie.dependencies

import dagger.Component
import ru.romanov.tonkoslovie.data.repositories.UserRepository
import ru.romanov.tonkoslovie.dependencies.modules.ApiModule
import ru.romanov.tonkoslovie.ui.screens.login.LoginActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface ApplicationComponent {

    fun inject(loginActivity: LoginActivity)

    fun getUserRepository(): UserRepository

}