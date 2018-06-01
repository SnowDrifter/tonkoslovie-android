package ru.romanov.tonkoslovie.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import ru.romanov.tonkoslovie.data.models.lesson.Lesson

interface ContentApiService {

    @GET("/api/content/lessons")
    fun lessons(): Observable<List<Lesson>>

}