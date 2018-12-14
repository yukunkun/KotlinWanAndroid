package com.yukun.kotlinwanandroid.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.beans.CommentNet
import java.util.*

/**
 * author: kun .
 * date:   On 2018/12/14
 */
class LVAdapter(context:Context,mData:List<CommentNet>) : BaseAdapter() {
    var context=context
    var mData=mData
    var random=Random()
    var mList = arrayOf(R.color.color_2b2b2b, R.color.color_2e4eef, R.color.colorPrimary, R.color.color_ff2323, R.color.color_ff01bb, R.color.color_ff4081, R.color.color_ffe100, R.color.color_494949, R.color.color_30f209, R.color.color_30f209)

    override fun getView(position: Int, convertView : View?, parent: ViewGroup?): View {
        var holder:MyHolder
        var view:View
        if(convertView == null){
            view = View.inflate(context,R.layout.common_item, null)
            holder=MyHolder(view)
            view.tag=holder
        }else {
            view = convertView
            holder  = view.tag as MyHolder
        }
        holder.tv.text=mData[position].name
        val nextInt = random.nextInt(mList.size)
        holder.tv.setTextColor(context.resources.getColor(mList[nextInt]))
        return view
    }

    override fun getItem(position: Int): Any {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return mData.size
    }

    class MyHolder(view:View){
        var tv:TextView=view.findViewById(R.id.tv_msg)
    }
}