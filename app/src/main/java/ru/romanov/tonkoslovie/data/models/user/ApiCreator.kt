package ru.romanov.tonkoslovie.data.models.user

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.data.models.user.api.UserApiService
import ru.romanov.tonkoslovie.utils.ResourceUtils

object ApiCreator {

    fun getUserApi() : UserApiService {
        return getRetrofit().create<UserApiService>(UserApiService::class.java)
    }

    private fun getRetrofit() : Retrofit {
       return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ResourceUtils.getString(R.string.api_url))
                .build()
    }
}