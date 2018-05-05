package ru.romanov.tonkoslovie.dependencies.modules


import dagger.Module
import dagger.Provides
import ru.romanov.tonkoslovie.data.repositories.UserRepository
import ru.romanov.tonkoslovie.data.repositories.UserRepositoryImpl
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }

}