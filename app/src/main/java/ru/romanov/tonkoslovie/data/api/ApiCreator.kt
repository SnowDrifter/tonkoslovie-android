package ru.romanov.tonkoslovie.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.TonkoslovieApplication
import ru.romanov.tonkoslovie.utils.ResourceUtils

object ApiCreator {

    fun getUserApi(): UserApiService {
        return getRetrofit().create<UserApiService>(UserApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())

            val token = TonkoslovieApplication.instance.applicationComponent.getUserRepository().getToken()
            if (token.isNotEmpty()) {
                requestBuilder.header("Authorization", token)
            }

            chain.proceed(requestBuilder.build())
        }

        val client = builder.build()

        return Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ResourceUtils.getString(R.string.api_url))
                .build()
    }
}