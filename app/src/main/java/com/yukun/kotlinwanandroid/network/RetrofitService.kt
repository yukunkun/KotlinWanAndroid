package com.yukun.kotlinwanandroid.network

import com.yukun.kotlinwanandroid.beans.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import top.jowanxu.wanandroidclient.bean.Data

/**
 * author: kun .
 * date:   On 2018/12/13
 */
interface RetrofitService {

    @GET("article/list/{page}/json")
    fun getIndexListCall(@Path("page") page:Int) : Call<HomeListResponse<Data>>

    /**
     * banner
     *
     */
    @GET("banner/json")
    fun banner():Call<HomeListResponse<List<BannerBean>>>
    /**
     * 首页列表
     */
    @GET("article/list/{page}/json")
    fun getIndexList(@Path("page") page:Int) : Call<HomeListResponse<Data>>

    /**
     * 登录
     */
    @POST("user/login")
    fun login(@Query("username") username:String,@Query("password") password:String): Call<HomeListResponse<UserBean>>

    /**
     * 注册
     */
    @POST("user/register")
    fun register(@Query("username") username:String,@Query("password") password:String,@Query("repassword") repassword:String): Call<HomeListResponse<UserBean>>

    /**
     * 体系列表
     */
    @GET("tree/json")
    fun getKnowledgeList() : Call<HomeListResponse<List<KnowledgeBean>>>

    /**
     * 热词
     */
    @GET("hotkey/json")
    fun getHotSearch() : Call<HomeListResponse<List<HotWorkBean>>>

    /**
     * 热词
     */
    @GET("friend/json")
    fun getCommentNet() : Call<HomeListResponse<List<CommentNet>>>

    /**
     * 公众号
     */
    @GET("wxarticle/chapters/json")
    fun getWeChat():Call<HomeListResponse<List<WeChatBean>>>
    /**
     * 公众号文章
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getWeChatArtical(@Path("id") id :Int, @Path("page") page:Int):Call<HomeListResponse<WeChatDetailBean>>
    /**
     * search
     */
    @POST("article/query/{page}/json")
    fun search(@Path("page") page:Int,@Query("k") key:String):Call<HomeListResponse<Data>>
    /**
     * 体系详情
     */
    @GET("article/list/{page}/json")
    fun getKnowledgeArtical( @Path("page") page:Int,@Query("cid") cid :Int):Call<HomeListResponse<Data>>
    /**
     * 项目
     *
     */
    @GET("article/listproject/{page}/json")
    fun getProject( @Path("page") page:Int):Call<HomeListResponse<Data>>
    /**
     * 收藏列表
     *
     */
    @GET("lg/collect/list/{page}/json")
    fun  collectlist(@Path("page") page:Int):Call<HomeListResponse<Data>>
    /**
     * 添加收藏
     */
    @POST("lg/collect/{id}/json")
    fun addCollect(@Path("id") id:Int,@Query("originId") originId:Int):Call<Response<String>>
    /**
     * 取消收藏
     */
    @POST("lg/uncollect/{id}/json")
    fun removeCollect(@Path("id") id:Int,@Query("originId") originId:Int):Call<Response<String>>
}