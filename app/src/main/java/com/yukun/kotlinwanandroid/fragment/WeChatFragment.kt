package com.yukun.kotlinwanandroid.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.MineActivity
import com.yukun.kotlinwanandroid.activity.SearchActivity
import com.yukun.kotlinwanandroid.adapter.VPAdapter
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.beans.WeChatBean
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import kotlinx.android.synthetic.main.wechat_fragment.*
import retrofit2.Call

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class WeChatFragment(): BaseFragment() {

    var fragments= mutableListOf<WeChatDetailFragment>()
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

    private fun initLayout(data: List<WeChatBean>) {
        for(index in 0 until data.size){
            val tab = tl_layout.newTab()
            tab.text=data[index].name
            tl_layout.addTab(tab)
        }
        tl_layout.getTabAt(0)!!.select()

        for (index in 0 until data.size){
            val weChatDetailFragment = WeChatDetailFragment.getInstance()
            weChatDetailFragment!!.getData(data[index])
            fragments.add(weChatDetailFragment)
        }

        var adapter=VPAdapter(childFragmentManager,data,fragments)
        viewpager.adapter=adapter
        tl_layout.setupWithViewPager(viewpager)

    }


    override fun initListener() {
        tl_layout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewpager.currentItem= p0!!.position
            }
        })
        viewpager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                tl_layout.getTabAt(p0)!!.select()
            }
        })
        iv_me.setOnClickListener {
            var intent=Intent(context,MineActivity::class.java)
            startActivity(intent)
        }
        iv_search.setOnClickListener {
            var intent=Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun initLayout(): Int {
        return R.layout.wechat_fragment
    }
}