package com.yukun.kotlinwanandroid.fragment

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class KnowledgeFragment(): BaseFragment() {

    companion object {
        var knowledgeFragment : KnowledgeFragment ?= null

        fun getInstance():KnowledgeFragment{
            knowledgeFragment=KnowledgeFragment()
            return knowledgeFragment!!
        }
    }
    override fun initUI(inflate: View) {
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun initLayout(): Int {
        return R.layout.knowledge_fragment
    }

}