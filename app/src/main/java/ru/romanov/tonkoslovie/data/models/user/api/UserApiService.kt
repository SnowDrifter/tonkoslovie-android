package ru.romanov.tonkoslovie.data.models.user.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import ru.romanov.tonkoslovie.data.models.user.LoginResponse
import ru.romanov.tonkoslovie.data.models.user.UserRequest
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit


interface UserApiService {

    @POST("/api/user/login")
    fun login(@Body request: UserRequest): Observable<LoginResponse>

    companion object {
        fun create(): UserApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://10.0.2.2:8080")
                    .build()

            return retrofit.create(UserApiService::class.java)
        }
    }
}

