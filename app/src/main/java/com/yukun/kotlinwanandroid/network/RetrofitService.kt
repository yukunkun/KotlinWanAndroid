package com.yukun.kotlinwanandroid.network

import com.yukun.kotlinwanandroid.beans.HomeListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import top.jowanxu.wanandroidclient.bean.Data

/**
 * author: kun .
 * date:   On 2018/12/13
 */
interface RetrofitService {

    @GET("article/list/{page}/json")
    fun getIndexListCall(@Path("page") page:Int) : Call<HomeListResponse<Data>>

    @GET("article/list/{page}/json")
    fun getIndexList(@Path("page") page:Int) : Call<HomeListResponse<Data>>

}