package com.example.demoandroid.api

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T?
)