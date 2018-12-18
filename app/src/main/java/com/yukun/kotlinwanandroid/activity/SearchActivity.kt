package com.yukun.kotlinwanandroid.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.adapter.RVIndexAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.impl.CollectClickCallBack
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import top.jowanxu.wanandroidclient.bean.Data

class SearchActivity : BaseActivity() {
    var key: String ?=null
    var page=0
    var mData= mutableListOf<Data.Datas>()
    var indexRvAdapter:RVIndexAdapter?=null
    override fun initLayout(): Int {
       return R.layout.activity_search
    }

    override fun initUI() {
        key = intent.getStringExtra("key")
        if(!TextUtils.isEmpty(key)){
            ed_text.setText(key)
            search(key!!)
        }
    }

    override fun initData() {
        smartLayout.setPrimaryColors(Color.WHITE, Color.BLUE)
        smartLayout.setBackgroundResource(R.color.colorPrimary)
        var linlayoutManager=LinearLayoutManager(this)
        recyclerview.layoutManager=linlayoutManager
        indexRvAdapter= RVIndexAdapter(mData,this)
        recyclerview.adapter=indexRvAdapter
    }

    fun search(key:String){
        RetrofitFactory.getInstance().search(page,key,object :BaseCallBack<Data>{
            override fun onSuccess(data: Data) {
                if(data.datas!!.size==0){
                    ToastUtils.show("没有相关内容")
                    return
                }
                mData.addAll(data.datas!!.toMutableList())
                //完成
                if (page == 0) {
                    smartLayout.finishRefresh()
                } else {
                    smartLayout.finishLoadmore()
                }
                indexRvAdapter!!.notifyDataSetChanged()
            }

            override fun onBadRespone(msg: String) {

            }

            override fun onFailture(call: Call<HomeListResponse<Data>>?, t: Throwable?) {
            }
        })
    }

    override fun initListener() {
        ed_text.setOnEditorActionListener(object :TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(!TextUtils.isEmpty(ed_text.text.toString())){
                        search(ed_text.text.toString())
                    }else{
                        ToastUtils.show("搜索不能为空")
                    }
                }
                return false
            }

        })

        tv_cancel.setOnClickListener {
            finish()
        }

        smartLayout.setOnRefreshListener {
            page=0
            mData.clear()
            if(!TextUtils.isEmpty(ed_text.text.toString())){
                search(ed_text.text.toString())
            }else{
                ToastUtils.show("搜索不能为空")
            }
        }
        smartLayout.setOnLoadmoreListener {
            page++
            if(!TextUtils.isEmpty(ed_text.text.toString())){
                search(ed_text.text.toString())
            }else{
                ToastUtils.show("搜索不能为空")
            }
        }
        indexRvAdapter!!.getmClickCallBack(object : CollectClickCallBack {
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
            }

            override fun onResponse(call: Call<Response<String>>?, response: Response<Response<String>>?) {
//                Log.i("===========", response!!.body().toString())
                ToastUtils.show("收藏成功")
            }
        })
    }

    private fun removeCollect(originId: Int, id: Int) {
        RetrofitFactory.getInstance().removeCollect(originId,id,object : Callback<Response<String>> {
            override fun onFailure(call: Call<Response<String>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Response<String>>?, response: Response<Response<String>>?) {
//                Log.i("===========remove", response!!.body().toString())
            }
        })
    }

}
