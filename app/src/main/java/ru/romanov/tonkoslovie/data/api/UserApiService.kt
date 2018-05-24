package ru.romanov.tonkoslovie.data.api

import io.reactivex.Observable
import retrofit2.http.*
import ru.romanov.tonkoslovie.data.models.user.LoginResponse
import ru.romanov.tonkoslovie.data.models.user.User
import ru.romanov.tonkoslovie.data.models.user.UserRequest


interface UserApiService {

    @POST("/api/user/login")
    fun login(@Body request: UserRequest): Observable<LoginResponse>

    @GET("/api/user/users")
    fun users(): Observable<List<User>>

    @GET("/api/user")
    fun user(): Observable<User>

}

