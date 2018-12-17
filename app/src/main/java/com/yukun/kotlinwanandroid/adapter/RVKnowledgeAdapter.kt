package com.yukun.kotlinwanandroid.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.activity.KnowledgeActivity
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
            var ids=""
            for (index in 0..children!!.size-1){
                text+=children[index].name+"/"
                ids+=""+children[index].id+"/"
            }
            holder.itemView.tv_catgroy.text=text
            holder.itemView.setOnClickListener {
                var intent=Intent(context,KnowledgeActivity::class.java)
                intent.putExtra("text",text!!)
                intent.putExtra("cid", ids)
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}