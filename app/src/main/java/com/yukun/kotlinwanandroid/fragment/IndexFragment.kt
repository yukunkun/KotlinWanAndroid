package com.yukun.kotlinwanandroid.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.MineActivity
import com.yukun.kotlinwanandroid.activity.SearchActivity
import com.yukun.kotlinwanandroid.adapter.RVIndexAdapter
import com.yukun.kotlinwanandroid.adapter.RVIndexBannerAdapter
import com.yukun.kotlinwanandroid.beans.BannerBean
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.impl.CollectClickCallBack
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.index_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.jowanxu.wanandroidclient.bean.Data

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class IndexFragment: BaseFragment() {
    var page=0
    var mDataList= mutableListOf<Data.Datas>()
    var mDataBanner= mutableListOf<BannerBean>()
    var linlayoutManager:LinearLayoutManager?=null
    var indexRvAdapter: RVIndexBannerAdapter?=null

    companion object {
        var indexFragment : IndexFragment ?= null
        fun getInstance():IndexFragment{
            indexFragment=IndexFragment()
            return indexFragment!!
        }
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {
        linlayoutManager= LinearLayoutManager(context)
        recyclerview.layoutManager=linlayoutManager
        indexRvAdapter= RVIndexBannerAdapter(mDataList,mDataBanner, context)
        recyclerview.adapter=indexRvAdapter
        refreshLayout.setPrimaryColors(Color.WHITE, Color.BLUE)
        refreshLayout.setBackgroundResource(R.color.colorPrimary)
    }

    override fun initData() {
        //直接获取数据的网络请求
//        RetrofitFactory.getInstance().getIndexListCall(0 ,object : retrofit2.Callback<HomeListResponse<Data>>{
//            override fun onFailure(call: Call<HomeListResponse<Data>>?, t: Throwable?) {
//
//            }
//
//            override fun onResponse(call: Call<HomeListResponse<Data>>?, response: Response<HomeListResponse<Data>>?) {
//            }
//        })

        RetrofitFactory.getInstance().getBanner(object : BaseCallBack<List<BannerBean>>{
            override fun onSuccess(data: List<BannerBean>) {
                mDataBanner.addAll(data)
                getIndexData()
            }

            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<List<BannerBean>>>?, t: Throwable?) {

            }
        })
    }

    private fun getIndexData() {
        RetrofitFactory.getInstance().getIndexList(page, object : BaseCallBack<Data> {
            override fun onSuccess(data: Data) {
                //.toMutableList()转换成可变集合，再调用addAll
                mDataList.addAll(data.datas!!.toMutableList())

                //完成
                if (page == 0) {
                    refreshLayout!!.finishRefresh()
                } else {
                    refreshLayout!!.finishLoadmore()
                }
                av_load.visibility = View.GONE
                indexRvAdapter!!.notifyDataSetChanged()
            }

            override fun onBadRespone(msg: String) {

            }

            override fun onFailture(call: Call<HomeListResponse<Data>>?, t: Throwable?) {
                ToastUtils.show(t.toString())
            }

        })
    }

    override fun initListener() {
        refreshLayout.setOnRefreshListener(object : OnRefreshListener{
            override fun onRefresh(refreshlayout: RefreshLayout?) {
                page=0
                mDataList.clear()
                getIndexData()
            }
        })

        refreshLayout.setOnLoadmoreListener (object :OnLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                page++
                getIndexData()
//                initData()
            }
        } )

        iv_me.setOnClickListener {
            var intent=Intent(context,MineActivity::class.java)
            startActivity(intent)
        }
        iv_search.setOnClickListener {
            var intent=Intent(context,SearchActivity::class.java)
            startActivity(intent)
        }

        indexRvAdapter!!.getmClickCallBack(object :CollectClickCallBack{
            override fun clickCallBack(originId: Int, id: Int, isCollect: Boolean) {
                if(!isCollect){
                    addCollect(originId,id)
                }else{
                    removeCollect(originId,id)
                }
            }
        })
    }

    private fun addCollect(originId: Int, id: Int) {
        RetrofitFactory.getInstance().addCollect(originId,id,object :Callback<Response<String>>{
            override fun onFailure(call: Call<Response<String>>?, t: Throwable?) {
                Log.i("===========",t.toString())
            }

            override fun onResponse(call: Call<Response<String>>?, response: Response<Response<String>>?) {
//                Log.i("===========", response!!.body().toString())
                ToastUtils.show("收藏成功")
            }
        })
    }

    private fun removeCollect(originId: Int, id: Int) {
        RetrofitFactory.getInstance().removeCollect(originId,id,object :Callback<Response<String>>{
            override fun onFailure(call: Call<Response<String>>?, t: Throwable?) {
                Log.i("===========remove",t.toString())
            }

            override fun onResponse(call: Call<Response<String>>?, response: Response<Response<String>>?) {
//                Log.i("===========remove", response!!.body().toString())
            }
        })
    }

    override fun initLayout(): Int {
        return R.layout.index_fragment
    }

    override fun onDestroy() {
        super.onDestroy()
        indexRvAdapter!!.flag=false
    }
}