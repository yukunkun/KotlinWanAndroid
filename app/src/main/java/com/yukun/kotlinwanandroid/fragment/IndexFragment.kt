package com.yukun.kotlinwanandroid.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.MineActivity
import com.yukun.kotlinwanandroid.activity.SearchActivity
import com.yukun.kotlinwanandroid.adapter.RVIndexAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.index_fragment.*
import retrofit2.Call
import top.jowanxu.wanandroidclient.bean.Data
import java.util.*

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class IndexFragment: BaseFragment() {

    var page=0
    var mDataList= mutableListOf<Data.Datas>()
    var linlayoutManager:LinearLayoutManager?=null
    var indexRvAdapter:RVIndexAdapter?=null

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
        indexRvAdapter= RVIndexAdapter(mDataList, context)
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

        RetrofitFactory.getInstance().getIndexList(page,object : BaseCallBack<Data>{
            override fun onSuccess(data: Data) {
                //.toMutableList()转换成可变集合，再调用addAll
                mDataList.addAll(data.datas!!.toMutableList())
                //完成
                if (page == 0) {
                    refreshLayout.finishRefresh()
                } else {
                    refreshLayout.finishLoadmore()
                }
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
                initData()
            }
        })

        refreshLayout.setOnLoadmoreListener (object :OnLoadmoreListener{
            override fun onLoadmore(refreshlayout: RefreshLayout?) {
                page++
                initData()
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
    }

    override fun initLayout(): Int {
        return R.layout.index_fragment
    }

}