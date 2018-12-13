package com.yukun.kotlinwanandroid.network
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author: kun .
 * date:   On 2018/12/13
 */
class RetrofitManager {
    var mOkHttpClient:OkHttpClient?=null
    var mRetrofit:Retrofit?=null
    var baseUrl="http://www.wanandroid.com/"

    companion object {

        var retrofitManager: RetrofitManager?=null
        var mRetrofitService: RetrofitService?=null

        //单例
        fun getInstance() : RetrofitManager {
            if(retrofitManager ==null){
                retrofitManager = RetrofitManager() //会调用 constructor 构造函数
            }
            return retrofitManager!!
        }
    }

    //构造函数
     constructor(){
        initHttpClient()
        initHttpRetrofit()
        initApiService()
     }

    private fun initHttpClient() {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getLogInterceptor())
        builder.connectTimeout(10000,TimeUnit.SECONDS)
        builder.writeTimeout(10000,TimeUnit.SECONDS)
        builder.readTimeout(10000,TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        mOkHttpClient=builder.build()
    }

    //日志打印
    private fun getLogInterceptor(): Interceptor {
        var loggerInterceptor=HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
            override fun log(message: String?) {
                Log.i("okHttp:",message)
            }
        })
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return  loggerInterceptor!!
    }

    private fun initHttpRetrofit() {
        val builder = Retrofit.Builder()
        builder.baseUrl(baseUrl)
        builder.client(mOkHttpClient)
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        mRetrofit=builder.build()
    }

    private fun initApiService() {
        mRetrofitService= mRetrofit!!.create(RetrofitService::class.java)
    }


    fun getService(): RetrofitService{
        return mRetrofitService!!
    }

}