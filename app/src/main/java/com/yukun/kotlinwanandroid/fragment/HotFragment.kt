package com.yukun.kotlinwanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.DetailActivity
import com.yukun.kotlinwanandroid.activity.MineActivity
import com.yukun.kotlinwanandroid.activity.SearchActivity
import com.yukun.kotlinwanandroid.adapter.LVAdapter
import com.yukun.kotlinwanandroid.beans.CommentNet
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.beans.HotWorkBean
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.hot_fragment.*
import retrofit2.Call

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class HotFragment(): BaseFragment() {

    var mData= mutableListOf<CommentNet>()

    var adapter:LVAdapter?=null
    companion object {
        var hotFragment : HotFragment ?= null
        fun getInstance():HotFragment{
            hotFragment=HotFragment()
            return hotFragment!!
        }
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {
        listview.isFocusable=false
        adapter=LVAdapter(context!!,mData)
        listview.adapter=adapter
    }

    override fun initData() {
        RetrofitFactory.getInstance().hotSearch(object : BaseCallBack<List<HotWorkBean>>{
            override fun onSuccess(data: List<HotWorkBean>) {
                initTagLayout(data)

            }

            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<List<HotWorkBean>>>?, t: Throwable?) {
            }
        })

        RetrofitFactory.getInstance().commentNet(object : BaseCallBack<List<CommentNet>>{
            override fun onSuccess(data: List<CommentNet>) {
                mData.addAll(data)
                adapter!!.notifyDataSetChanged()
            }
            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<List<CommentNet>>>?, t: Throwable?) {
            }

        })
    }

    private fun initTagLayout(data: List<HotWorkBean>) {
        for (index in 0 until data.size){
            var tv=TextView(context)
            tv.text=data[index].name
            tv.setTextColor(context!!.resources.getColor(R.color.color_ff2323))
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(18, 15, 18, 15)
            tv.setPadding(15,8,15,8)
            tv.isClickable=true
            tv.textSize=14f
            tv.layoutParams=layoutParams
            tv.setBackgroundResource(R.drawable.shape_tag_back)
            taglayout.addView(tv)
            tv.setOnClickListener {
                ToastUtils.show(tv.text.toString())
            }
        }
    }

    override fun initListener() {
        iv_me.setOnClickListener {
            var intent= Intent(context, MineActivity::class.java)
            startActivity(intent)
        }
        iv_search.setOnClickListener {
            var intent=Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
        listview.setOnItemClickListener { parent, view, position, id ->
            var intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("url",mData[position].link)
            intent.putExtra("title",mData[position].name)
            context!!.startActivity(intent)
        }
    }

    override fun initLayout(): Int {
        return R.layout.hot_fragment
    }

}