package ru.romanov.tonkoslovie.data.models.user.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import ru.romanov.tonkoslovie.R
import ru.romanov.tonkoslovie.data.models.user.LoginResponse
import ru.romanov.tonkoslovie.data.models.user.UserRequest
import ru.romanov.tonkoslovie.utils.ResourceUtils


interface UserApiService {

    @POST("/api/user/login")
    fun login(@Body request: UserRequest): Observable<LoginResponse>

}

