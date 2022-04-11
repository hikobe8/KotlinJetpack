package com.hikobe8.kt.kthttp

import javax.annotation.processing.SupportedAnnotationTypes

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class GET(val value: String)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Query(val value: String)