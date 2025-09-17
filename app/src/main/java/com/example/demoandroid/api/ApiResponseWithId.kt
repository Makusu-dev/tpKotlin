package com.example.demoandroid.api

data class ApiResponseWithId<T>(
    val code: String,
    val message: String,
    val data: T?,
    val id: String
)