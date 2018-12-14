package com.yukun.kotlinwanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.TextView
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.DetailActivity
import com.yukun.kotlinwanandroid.activity.MineActivity
import com.yukun.kotlinwanandroid.adapter.LVAdapter
import com.yukun.kotlinwanandroid.beans.CommentNet
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.beans.HotWorkBean
import com.yukun.kotlinwanandroid.beans.WeChatBean
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import com.yukun.kotlinwanandroid.view.TagLayout
import kotlinx.android.synthetic.main.hot_fragment.*
import kotlinx.android.synthetic.main.wechat_fragment.*
import retrofit2.Call

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class WeChatFragment(): BaseFragment() {
    companion object {
        var weChatFragment : WeChatFragment ?= null
        fun getInstance():WeChatFragment{
            weChatFragment=WeChatFragment()
            return weChatFragment!!
        }
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {

    }

    override fun initData() {
        RetrofitFactory.getInstance().weChatChapter(object :BaseCallBack<List<WeChatBean>>{
            override fun onSuccess(data: List<WeChatBean>) {
                initLayout(data)
            }

            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<List<WeChatBean>>>?, t: Throwable?) {
            }

        })

    }

//    tabLayout.addTab(tabLayout.newTab());

    private fun initLayout(data: List<WeChatBean>) {
        for(index in 0 until data.size){
            val tab = tl_layout.newTab()
            tab.text=data[index].name
            tl_layout.addTab(tab)
        }
        tl_layout.getTabAt(0)!!.select()
    }


    override fun initListener() {

    }

    override fun initLayout(): Int {
        return R.layout.wechat_fragment
    }
}