package ru.romanov.tonkoslovie.data.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.romanov.tonkoslovie.data.models.user.LoginResponse
import ru.romanov.tonkoslovie.data.models.user.UserRequest
import ru.romanov.tonkoslovie.data.models.user.UserResponse


interface UserApiService {

    @POST("/api/user/login")
    fun login(@Body request: UserRequest): Observable<LoginResponse>

    @GET("/api/user/users")
    fun users(@Header("Authorization") jwtToken: String): Observable<List<UserResponse>>

}

