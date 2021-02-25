package com.ugurinci.appcentnewsapp.service

import com.ugurinci.appcentnewsapp.model.Everything
import io.reactivex.Observable
import retrofit2.http.*

interface NewsAPI {

    @GET("everything?q=besiktas&page=1&apiKey=90de41f9ae7745cda1b94c5e77fb5a76")
   // @GET("everything")

    //Retrofit
    //fun getData(): Call<Everything>
    //fun getData(@Query("q")q: String,@Query("page")page: String,@Query("apiKey")apiKey: String): Call<Everything>

    //RxJava
    fun getData(): Observable<Everything>

}