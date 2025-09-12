package com.example.demoandroid.article

import com.example.demoandroid.api.ApiResponse
import com.example.demoandroid.api.RetrofitTools
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("/articles")
    suspend fun getArticles(): ApiResponse<List<Article>>

    @GET("/articles/{id}")
    suspend fun getArticleById(@Path("id") id: String): ApiResponse<Article>


    object ArticleServiceApi {
        val articleService : ArticleService by lazy { RetrofitTools.Companion.retrofit.create(ArticleService::class.java)}
    }


}