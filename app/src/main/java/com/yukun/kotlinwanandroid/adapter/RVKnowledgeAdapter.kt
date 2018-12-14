package com.yukun.kotlinwanandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.beans.KnowledgeBean
import kotlinx.android.synthetic.main.knowledge_item.view.*

/**
 * author: kun .
 * date:   On 2018/12/14
 */
class RVKnowledgeAdapter(context:Context,data:List<KnowledgeBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context=context
    var data=data

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.knowledge_item, null)
        return MyHolder(inflate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is MyHolder){
            val knowledgeBean = data[position]
            holder.itemView.tv_title.text=knowledgeBean.name
            val children = knowledgeBean.children
            var text=""
            for (index in 0..children!!.size-1){
                text+=children[index].name+"/"
            }
            holder.itemView.tv_catgroy.text=text
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}