package com.yukun.kotlinwanandroid.network
import com.yukun.kotlinwanandroid.beans.HomeListResponse

/**
 * author: kun .
 * date:   On 2018/12/13
 */
abstract interface BaseCallBack<T> : retrofit2.Callback<HomeListResponse<T>> {

    override fun onFailure(call: retrofit2.Call<HomeListResponse<T>>?, t: Throwable?) {
        onFailture(call,t)
    }

    override fun onResponse(call: retrofit2.Call<HomeListResponse<T>>?, response: retrofit2.Response<HomeListResponse<T>>?) {
        if(response!!.isSuccessful){
            if(response.body().errorCode==0){
                onSuccess(response.body().data)
            }else{
                onBadRespone(response.body().errorMsg!!)
            }
        }
    }

    abstract fun onSuccess(data :T)
    abstract fun onBadRespone(msg:String)
    abstract fun onFailture(call: retrofit2.Call<HomeListResponse<T>>?, t: Throwable?)

}