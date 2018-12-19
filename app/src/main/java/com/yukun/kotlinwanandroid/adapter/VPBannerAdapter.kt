package com.yukun.kotlinwanandroid.adapter

import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.DetailActivity
import com.yukun.kotlinwanandroid.beans.BannerBean
import kotlinx.android.synthetic.main.banner_item_layout.view.*

/**
 * author: kun .
 * date:   On 2018/12/18
 */
class VPBannerAdapter(mData:List<BannerBean>) : PagerAdapter() {
    var mData=mData
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0==p1
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from (container.context).inflate(R.layout.banner_item_layout, null)
        Glide.with(container.context).load(mData[position].imagePath).into(view.iv_cover)
        view.tv_title.text=mData[position].title
        view.setOnClickListener {
            var intent=Intent(container.context,DetailActivity::class.java)
            intent.putExtra("title",mData[position].title)
            intent.putExtra("url",mData[position].url)
            container.context.startActivity(intent)
        }
        container.addView(view)
        return view
    }
}