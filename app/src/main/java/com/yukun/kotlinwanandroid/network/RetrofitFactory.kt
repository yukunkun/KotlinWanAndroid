package com.yukun.kotlinwanandroid.network

import com.yukun.kotlinwanandroid.beans.HomeListResponse
import retrofit2.Callback
import top.jowanxu.wanandroidclient.bean.Data

/**
 * author: kun .
 * date:   On 2018/12/13
 */
class RetrofitFactory {

    var mRetrofitService :RetrofitService?=null

    companion object {
        var mRetrofitFactory:RetrofitFactory?=null

        fun getInstance() : RetrofitFactory{
            if(mRetrofitFactory==null){
                mRetrofitFactory= RetrofitFactory() //会调用 constructor 构造函数
            }
            return mRetrofitFactory!!
        }
    }

    //构造函数
    constructor(){
        mRetrofitService=RetrofitManager.getInstance().getService()
    }

    fun getIndexListCall(page: Int,callBack : Callback<HomeListResponse<Data>>){
        mRetrofitService!!.getIndexListCall(page).enqueue(callBack)
    }


    fun getIndexList(page: Int, callBack: BaseCallBack<Data>){
        mRetrofitService!!.getIndexList(page).enqueue(callBack)
    }
}