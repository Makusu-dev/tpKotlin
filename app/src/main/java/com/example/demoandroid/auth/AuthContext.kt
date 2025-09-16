package com.example.demoandroid.auth

class AuthContext(var token: String = "") {

    // Singleton
    companion object {
        val instance : AuthContext by lazy { AuthContext() }

        fun get() : AuthContext {
            return instance
        }
    }

    fun setAuthToken(newToken: String){
        token = newToken

        println("Stocker le token en cache : ${token}")
    }
}