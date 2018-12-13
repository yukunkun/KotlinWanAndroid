package com.yukun.kotlinwanandroid.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * author: kun .
 * date:   On 2018/12/13
 */
class ReadCookiesInterceptor : Interceptor {
    //todo
    override fun intercept(chain: Interceptor.Chain?): Response {
        var  builder = chain!!.request().newBuilder()
        val request = chain.request()
        val url = chain.request().url()

        return chain.proceed(builder.build())
    }
}