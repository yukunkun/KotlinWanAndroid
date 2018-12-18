package com.yukun.kotlinwanandroid.activity

import android.app.ProgressDialog
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ProgressBar
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.adapter.RVCollectAdapter
import com.yukun.kotlinwanandroid.adapter.RVIndexAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.impl.CollectClickCallBack
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_collect.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.jowanxu.wanandroidclient.bean.Data

class CollectActivity : BaseActivity() {
    var page=0
    var datas= mutableListOf<Data.Datas>()
    var adapter: RVCollectAdapter?=null
    var progressbar:ProgressDialog?= null
    override fun initLayout(): Int {
        return R.layout.activity_collect
    }

    override fun initUI() {
        progressbar=ProgressDialog(this)
        progressbar!!.setMessage("加载中。。。")
        progressbar!!.show()
        smartrefreshlayout.setPrimaryColors(Color.WHITE, Color.BLUE)
        smartrefreshlayout.setBackgroundResource(R.color.colorPrimary)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager=linearLayoutManager
        adapter= RVCollectAdapter(datas,this)
        recyclerview.adapter=adapter
    }

    override fun initData() {
        RetrofitFactory.getInstance().collectList(page,object :BaseCallBack<Data>{
            override fun onSuccess(data: Data) {
                datas.addAll(data.datas!!.toMutableList())
                adapter!!.notifyDataSetChanged()
                if(page==0){
                    smartrefreshlayout.finishRefresh()
                }else{
                    smartrefreshlayout.finishLoadmore()
                }
                progressbar!!.dismiss()
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
        iv_back.setOnClickListener {
            finish()
        }
        adapter!!.getmClickCallBack(object : CollectClickCallBack {
            override fun clickCallBack(originId: Int, id: Int, isCollect: Boolean) {
                removeCollect(originId,id)
            }
        })
    }

    private fun removeCollect(originId: Int, id: Int) {
        progressbar!!.show()
        RetrofitFactory.getInstance().removeCollect(originId,id,object :Callback<Response<String>>{
            override fun onFailure(call: Call<Response<String>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Response<String>>?, response: Response<Response<String>>?) {
                ToastUtils.show("取消收藏成功")
                initData()
            }

        })
    }
}
