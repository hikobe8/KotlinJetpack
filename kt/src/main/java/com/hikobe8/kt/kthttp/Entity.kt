package com.hikobe8.kt.kthttp


//{ "message":"ok",
// "nu":"11111111111",
// "ischeck":"1",
// "com":"yuantong",
// "status":"200",
// "condition":"F00",
// "state":"3",
// "data":[{ "time":"2022-04-06 00:11:54", "context":"查无结果", "ftime":"2022-04-06 00:11:54" }] }

data class QueryData(
    var data: List<Data>?,
)

data class Data(
    var time: String?,
    var context: String?,
    var ftime: String?
)