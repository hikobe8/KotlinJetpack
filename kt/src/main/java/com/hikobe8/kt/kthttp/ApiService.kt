package com.hikobe8.kt.kthttp

interface ApiService {

    @GET("/query")
    fun query(
        @Query("type") type: String,
        @Query("postid") postId: String
    ): QueryData

}