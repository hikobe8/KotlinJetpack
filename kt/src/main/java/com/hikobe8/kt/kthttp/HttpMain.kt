package com.hikobe8.kt.kthttp

fun main(args: Array<String>) {
    val api: ApiService = KtHttpV1.create(ApiService::class.java)
    val data = api.query(type = "yuantong", postId = "88888888")
    println(data)
}