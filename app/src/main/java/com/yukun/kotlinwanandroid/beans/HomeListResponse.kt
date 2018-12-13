package com.yukun.kotlinwanandroid.beans

data class HomeListResponse<T>(
    var errorCode: Int,
    var errorMsg: String?,
    var data: T
)