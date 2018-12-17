package com.yukun.kotlinwanandroid.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.adapter.RVIndexAdapter
import com.yukun.kotlinwanandroid.adapter.RVWeChatAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.beans.WeChatBean
import com.yukun.kotlinwanandroid.beans.WeChatDetailBean
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import kotlinx.android.synthetic.main.wechat_detail_fragment.*
import retrofit2.Call
import top.jowanxu.wanandroidclient.bean.Data

/**
 * author: kun .
 * date:   On 2018/12/17
 */
class WeChatDetailFragment() : BaseFragment() {
    var wechatBean:WeChatBean?=null
    var page=1
    var datas= mutableListOf<WeChatDetailBean.WeChatDetailBeans>()
    var adapter:RVWeChatAdapter?=null
    companion object {
        var weChatFragment :WeChatDetailFragment?=null
        fun getInstance(): WeChatDetailFragment? {
            weChatFragment= WeChatDetailFragment()
            return weChatFragment
        }
    }

    fun getData(wechatBean :WeChatBean){
        this.wechatBean= wechatBean
    }

    override fun initLayout(): Int {
        return R.layout.wechat_detail_fragment
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {
        smartrefreshlayout.setPrimaryColors(Color.WHITE, Color.BLUE)
        smartrefreshlayout.setBackgroundResource(R.color.colorPrimary)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager=linearLayoutManager
        adapter= RVWeChatAdapter(datas,context)
        recyclerview.adapter=adapter
    }

    override fun initData() {
        RetrofitFactory.getInstance().weChatArticle(wechatBean!!.id,page,object : BaseCallBack<WeChatDetailBean>{
            override fun onSuccess(data: WeChatDetailBean) {
                datas.addAll(data.datas!!.toMutableList())
                adapter!!.notifyDataSetChanged()
                if(page==1){
                    smartrefreshlayout.finishRefresh()
                }else{
                    smartrefreshlayout.finishLoadmore()
                }
            }

            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<WeChatDetailBean>>?, t: Throwable?) {
            }

        })
    }

    override fun initListener() {
        smartrefreshlayout.setOnLoadmoreListener {
            page++
            initData()
        }
        smartrefreshlayout.setOnRefreshListener {
            page=1
            datas.clear()
            initData()
        }
    }
}