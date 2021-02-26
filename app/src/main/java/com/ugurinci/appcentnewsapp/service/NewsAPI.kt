package com.ugurinci.appcentnewsapp.service

import com.ugurinci.appcentnewsapp.model.News
import io.reactivex.Observable
import retrofit2.http.*

interface NewsAPI {

    @GET("everything?q=besiktas&page=1&apiKey=90de41f9ae7745cda1b94c5e77fb5a76")
    fun getData(): Observable<News>

    @GET("everything")
    fun searchData(@Query("q")q: String,@Query("page")page: String,@Query("apiKey")apiKey: String): Observable<News>

}