package com.yukun.kotlinwanandroid.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.adapter.RVIndexAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import kotlinx.android.synthetic.main.activity_knowledge.*
import kotlinx.android.synthetic.main.wechat_detail_fragment.*
import retrofit2.Call
import top.jowanxu.wanandroidclient.bean.Data
import kotlin.concurrent.fixedRateTimer

/**
 * author: kun .
 * date:   On 2018/12/17
 */
class KnowledgeDatailFragment : BaseFragment() {
    var cid:Int?=null
    var page=0
    var datas= mutableListOf<Data.Datas>()
    var adapter:RVIndexAdapter?=null
    companion object {
        var detailfragment :KnowledgeDatailFragment?=null
        fun getInstance(): KnowledgeDatailFragment? {
            detailfragment= KnowledgeDatailFragment()
            return detailfragment
        }
    }

    fun getData(cid :Int){
        this.cid= cid
    }

    override fun initLayout(): Int {
        return R.layout.wechat_detail_fragment
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {
        smartrefreshlayout.setPrimaryColors(Color.WHITE, Color.BLUE)
        smartrefreshlayout.setBackgroundResource(R.color.colorPrimary)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager=linearLayoutManager
        adapter= RVIndexAdapter(datas,context)
        recyclerview.adapter=adapter
    }

    override fun initData() {
        RetrofitFactory.getInstance().knowledgeDetail(page, cid!!,object :BaseCallBack<Data>{
            override fun onSuccess(data: Data) {
                datas.addAll(data.datas!!.toMutableList())
                adapter!!.notifyDataSetChanged()
                if(page==0){
                    smartrefreshlayout.finishRefresh()
                }else{
                    smartrefreshlayout.finishLoadmore()
                }
            }

            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<Data>>?, t: Throwable?) {
            }

        })
    }

    override fun initListener() {
        smartrefreshlayout.setOnLoadmoreListener {
            page++
            initData()
        }
        smartrefreshlayout.setOnRefreshListener {
            page=0
            datas.clear()
            initData()
        }
    }
}