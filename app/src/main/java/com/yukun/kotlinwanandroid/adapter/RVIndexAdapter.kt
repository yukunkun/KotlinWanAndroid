package com.yukun.kotlinwanandroid.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.DetailActivity
import kotlinx.android.synthetic.main.index_item.view.*
import top.jowanxu.wanandroidclient.bean.Data
import java.util.*

/**
 * author: kun .
 * date:   On 2018/12/13
 */
class RVIndexAdapter(mListData : List<Data.Datas>,context:Context?) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mListData : List<Data.Datas> ?=mListData
    private var context=context
    var random : Random=Random()
    internal var mList = arrayOf(R.color.color_2b2b2b, R.color.color_2e4eef, R.color.colorPrimary, R.color.color_ff2323, R.color.color_ff01bb, R.color.color_ff4081, R.color.color_ffe100, R.color.color_30f209, R.color.color_30f209)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.index_item,null)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return mListData!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyHolder){
            holder.itemView.tv_name.text= mListData!![position].chapterName
            //有标签
            holder.itemView.tv_content.text= Html.fromHtml(mListData!![position].title)
            holder.itemView.tv_time.text=mListData!![position].niceDate
            holder.itemView.tv_class.text=mListData!![position].chapterName
            val nextInt = random!!.nextInt(mList.size)
            holder.itemView.tv_name.setTextColor(context!!.resources.getColor(mList[nextInt]))
            holder.itemView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    var intent=Intent(context,DetailActivity::class.java)
                    intent.putExtra("url", mListData!![position].link)
                    intent.putExtra("title",mListData!![position].title)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context!!.startActivity(intent)
                }

            })
        }
    }

    class MyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

//        private var tv_name :TextView = itemView!!.findViewById(R.id.tv_name)

    }
}