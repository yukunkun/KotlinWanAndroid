package com.yukun.kotlinwanandroid.network
import android.util.Log
import com.yukun.kotlinwanandroid.utils.Preference
import encodeCookie
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
    var baseUrl="https://www.wanandroid.com/"

    private  val SAVE_USER_LOGIN_KEY = "user/login"
    private  val SAVE_USER_REGISTER_KEY = "user/register"
    private  val SET_COOKIE_KEY = "set-cookie"
    private  val COOKIE_NAME = "Cookie"

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
        builder.addInterceptor {
            val request = it.request()
            val response = it.proceed(request)
            val requestUrl = request.url().toString()
            val domain = request.url().host()
            // set-cookie maybe has multi, login to save cookie
            if ((requestUrl.contains(SAVE_USER_LOGIN_KEY) || requestUrl.contains(
                            SAVE_USER_REGISTER_KEY
                    ))
                    && !response.headers(SET_COOKIE_KEY).isEmpty()) {
                val cookies = response.headers(SET_COOKIE_KEY)
                val cookie = encodeCookie(cookies)
                saveCookie(requestUrl, domain, cookie)
            }
            response
        }
        // set request cookie
        builder.addInterceptor {
            val request = it.request()
            val builder = request.newBuilder()
            val domain = request.url().host()
            // get domain cookie
            if (domain.isNotEmpty()) {
                val spDomain: String by Preference(domain, "")
                val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
                if (cookie.isNotEmpty()) {
                    builder.addHeader(COOKIE_NAME, cookie)
                }
            }
            it.proceed(builder.build())
        }

        mOkHttpClient=builder.build()
    }

    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    private fun saveCookie(url: String?, domain: String?, cookies: String) {
        url ?: return
        var spUrl: String by Preference(url, cookies)
        @Suppress("UNUSED_VALUE")
        spUrl = cookies
        domain ?: return
        var spDomain: String by Preference(domain, cookies)
        @Suppress("UNUSED_VALUE")
        spDomain = cookies
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