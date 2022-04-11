package com.hikobe8.kt.kthttp

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object KtHttpV1 {

    // 底层使用 OkHttp
    private var okHttpClient: OkHttpClient = OkHttpClient()

    // 使用 Gson 解析 JSON
    private var gson: Gson = Gson()

    // 这里以baseurl.com为例，实际上我们的KtHttpV1可以请求任意API
    var baseUrl = "http://www.kuaidi100.com"

    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf(service)
        ) { proxy, method, args ->
            val annotations = method.annotations
            for (annotation in annotations) {
                if (annotation is GET) {
                    val url = baseUrl + annotation.value
                    return@newProxyInstance invoke(url, method, args)
                }
            }
            return@newProxyInstance null
        } as T
    }

    private fun invoke(path: String, method: Method, args: Array<Any>): Any? {
        if (method.parameterAnnotations.size != args.size) {
            //带注解的参数数量和函数实际的参数数量不一致，直接返回null
            return null
        }
        var url = path;
        val parameterAnnotations = method.parameterAnnotations
        for (i in parameterAnnotations.indices) {
            for (parameterAnnotation in parameterAnnotations[i]) {
                if (parameterAnnotation is Query) {
                    if (url.contains("?")) {
                        url += "&${parameterAnnotation.value}=${args[i]}"
                    } else {
                        url += "?${parameterAnnotation.value}=${args[i]}"
                    }
                }
            }
        }
        println(url)
        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        val response = okHttpClient.newCall(request).execute()
        val genericReturnType = method.genericReturnType
        val body = response.body
        return gson.fromJson(body?.string(), genericReturnType)
    }

}
