package com.yukun.kotlinwanandroid.network

import com.yukun.kotlinwanandroid.beans.*
import com.yukun.kotlinwanandroid.fragment.HotFragment
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

    fun getKnowledge(callBack: BaseCallBack<List<KnowledgeBean>>){
        mRetrofitService!!.getKnowledgeList().enqueue(callBack)
    }


    fun login(username:String, password:String, callBack: BaseCallBack<UserBean>){
        mRetrofitService!!.login(username,password).enqueue(callBack)
    }

    fun register(username:String,password :String,repassword:String,callBack: BaseCallBack<UserBean>){
        mRetrofitService!!.register(username,password,repassword).enqueue(callBack)
    }

    fun hotSearch(callBack: BaseCallBack<List<HotWorkBean>>){
        mRetrofitService!!.getHotSearch().enqueue(callBack)
    }

    fun commentNet(callBack: BaseCallBack<List<CommentNet>>){
        mRetrofitService!!.getCommentNet().enqueue(callBack)
    }

    fun weChatChapter(callBack: BaseCallBack<List<WeChatBean>>){
        mRetrofitService!!.getWeChat().enqueue(callBack)
    }
}