package com.ugurinci.appcentnewsapp.model

import com.google.gson.annotations.SerializedName

data class Everything(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)