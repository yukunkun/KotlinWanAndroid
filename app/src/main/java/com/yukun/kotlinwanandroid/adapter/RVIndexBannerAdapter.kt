package com.yukun.kotlinwanandroid.adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import com.yukun.kotlinwanandroid.MyApp
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.DetailActivity
import com.yukun.kotlinwanandroid.beans.BannerBean
import com.yukun.kotlinwanandroid.impl.CollectClickCallBack
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.banner_item.view.*
import kotlinx.android.synthetic.main.index_item.view.*
import top.jowanxu.wanandroidclient.bean.Data
import java.util.*

/**
 * author: kun .
 * date:   On 2018/12/13
 */
class RVIndexBannerAdapter(mListData : List<Data.Datas>,mListBanner : List<BannerBean>, context:Context?) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mListData : List<Data.Datas> ?=mListData
    private var mListBanner:List<BannerBean> ?=mListBanner
    private var context=context
    var random : Random=Random()
    var count=0
    var th:Thread?=null
    var handler =Handler()
    var flag=true
    internal var mList = arrayOf(R.color.color_2b2b2b, R.color.color_2e4eef, R.color.colorPrimary, R.color.color_ff2323, R.color.color_ff01bb, R.color.color_ff4081, R.color.color_ffe100, R.color.color_30f209, R.color.color_30f209)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        if(p1==0){
            var view=LayoutInflater.from(context).inflate(R.layout.banner_item,null)
            return MyHolder(view)
        }else{
            var view=LayoutInflater.from(context).inflate(R.layout.index_item,null)
            return MyHolder2(view)
        }
    }

    override fun getItemCount(): Int {
        return mListData!!.size+1
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //banner
        if(holder is MyHolder){
            var mVPBannerAdapter=VPBannerAdapter(mListBanner!!)
            holder.itemView.viewpager.adapter=mVPBannerAdapter
            mVPBannerAdapter.notifyDataSetChanged()
            if(th==null){
                th=Thread(Runnable {
                    while (true){
                        Thread.sleep(4000)
                        count++
                        handler.post(object :Runnable{
                            override fun run() {
                                holder.itemView.viewpager.currentItem=count % mListBanner!!.size
                            }
                        })
                    }
                })
                th!!.start()
            }
        }else if(holder is MyHolder2){
            val datas = mListData!![position-1]
            holder.itemView.tv_name.text= datas.author
            //有标签
            holder.itemView.tv_content.text= Html.fromHtml(datas.title)
            holder.itemView.tv_time.text= datas.niceDate
            holder.itemView.tv_class.text= datas.chapterName
            if(datas.collect){
                holder.itemView.iv_collect.setImageResource(R.mipmap.collection_fill)
            }else{
                holder.itemView.iv_collect.setImageResource(R.mipmap.collection)
            }
            val nextInt = random!!.nextInt(mList.size)
            holder.itemView.tv_name.setTextColor(context!!.resources.getColor(mList[nextInt]))
            holder.itemView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    var intent=Intent(context,DetailActivity::class.java)
                    intent.putExtra("url", datas.link)
                    intent.putExtra("title", datas.title)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context!!.startActivity(intent)
                }
            })
            holder.itemView.iv_collect.setOnClickListener {
                if(MyApp.getInstance()!!.userBean!=null){
                    if(datas.collect){
                        holder.itemView.iv_collect.setImageResource(R.mipmap.collection)
                    }else{
                        holder.itemView.iv_collect.setImageResource(R.mipmap.collection_fill)
                    }
                    mClickCallBack!!.clickCallBack(datas.originId, datas.id, datas.collect)
                    datas.collect=!datas.collect
                }else{
                    ToastUtils.show("请先登录")
                }
            }
        }
    }

    class MyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
//        private var tv_name :TextView = itemView!!.findViewById(R.id.tv_name)
    }
    class MyHolder2(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
//        private var tv_name :TextView = itemView!!.findViewById(R.id.tv_name)
    }

    var mClickCallBack: CollectClickCallBack?=null
    fun getmClickCallBack(mClickCallBack:CollectClickCallBack){
        this.mClickCallBack=mClickCallBack
    }
}