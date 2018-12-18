package com.yukun.kotlinwanandroid.network

import com.yukun.kotlinwanandroid.beans.*
import retrofit2.Callback
import retrofit2.Response
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

    fun getBanner(callBack: BaseCallBack<List<BannerBean>>){
        mRetrofitService!!.banner().enqueue(callBack)
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

    fun weChatArticle(id:Int, page:Int, callBack: BaseCallBack<WeChatDetailBean>){
        mRetrofitService!!.getWeChatArtical(id,page).enqueue(callBack)
    }

    fun search(page:Int,key:String, callBack: BaseCallBack<Data>){
        mRetrofitService!!.search(page,key).enqueue(callBack)
    }

    fun knowledgeDetail(page:Int,cid:Int, callBack: BaseCallBack<Data>){
        mRetrofitService!!.getKnowledgeArtical(page,cid).enqueue(callBack)
    }

    fun projectDetail(page:Int, callBack: BaseCallBack<Data>){
        mRetrofitService!!.getProject(page).enqueue(callBack)
    }

    fun collectList(page:Int, callBack: BaseCallBack<Data>){
        mRetrofitService!!.collectlist(page).enqueue(callBack)
    }

    fun addCollect(originId:Int, id:Int, callBack: Callback<Response<String>>){
        mRetrofitService!!.addCollect(id,originId).enqueue(callBack)
    }

    fun removeCollect(originId:Int,id:Int, callBack: Callback<Response<String>>){
        mRetrofitService!!.removeCollect(id,originId).enqueue(callBack)
    }
}