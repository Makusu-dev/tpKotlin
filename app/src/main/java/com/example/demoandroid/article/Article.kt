package com.example.demoandroid.article


import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class Article(var id: String, var title: String, var desc: String, var author: String, var imgPath: String) {

}

