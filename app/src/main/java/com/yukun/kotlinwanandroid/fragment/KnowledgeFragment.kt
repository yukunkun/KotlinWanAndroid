package com.yukun.kotlinwanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.MineActivity
import com.yukun.kotlinwanandroid.adapter.RVKnowledgeAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.beans.KnowledgeBean
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import kotlinx.android.synthetic.main.index_fragment.view.*
import kotlinx.android.synthetic.main.knowledge_fragment.*
import retrofit2.Call

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class KnowledgeFragment(): BaseFragment() {
    lateinit var linearLayoutManager:LinearLayoutManager
    var rvKnowledgeAdapter:RVKnowledgeAdapter?=null
    var mData= mutableListOf<KnowledgeBean>()
    companion object {
        var knowledgeFragment : KnowledgeFragment ?= null

        fun getInstance():KnowledgeFragment{
            knowledgeFragment=KnowledgeFragment()
            return knowledgeFragment!!
        }
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager=linearLayoutManager
        rvKnowledgeAdapter = RVKnowledgeAdapter(context!!, mData)
        recyclerview.adapter=rvKnowledgeAdapter
    }

    override fun initData() {
        RetrofitFactory.getInstance().getKnowledge(object : BaseCallBack<List<KnowledgeBean>>{
            override fun onSuccess(data: List<KnowledgeBean>) {
                mData.addAll(data)
                //完成
                rvKnowledgeAdapter!!.notifyDataSetChanged()
            }

            override fun onBadRespone(msg: String) {

            }

            override fun onFailture(call: Call<HomeListResponse<List<KnowledgeBean>>>?, t: Throwable?) {
            }

        })
    }

    override fun initListener() {

        swipeItemLayout.setOnRefreshListener {
            swipeItemLayout.isRefreshing=false
        }

        iv_me.setOnClickListener {
            var intent= Intent(context, MineActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initLayout(): Int {
        return R.layout.knowledge_fragment
    }

}