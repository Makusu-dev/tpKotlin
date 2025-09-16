package com.example.demoandroid.auth

import com.example.demoandroid.api.ApiResponse
import com.example.demoandroid.api.RetrofitTools
import retrofit2.http.Body
import retrofit2.http.POST

    data class SignupRequest(var email: String, var pseudo: String, var password: String, var passwordConfirm: String, var cityCode: String, var city: String, var phone: String )
    data class SignUpResponse(var email: String, var pseudo: String, var password: String, var cityCode: String, var city: String, var phone: String )

interface AuthService {



    @POST("login")
    suspend fun login(@Body user: UserModelData): ApiResponse<String>

    @POST("signup")
    suspend fun signup(@Body user: SignupRequest ): ApiResponse<SignUpResponse>

    @POST("reset-password")
    suspend fun resetPassword(@Body user: UserModelData): ApiResponse<String>

    object AuthServiceApi {
        val authService : AuthService by lazy { RetrofitTools.Companion.retrofit.create(AuthService::class.java)}
    }
}