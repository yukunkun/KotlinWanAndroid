package com.yukun.kotlinwanandroid.activity

import android.util.Log
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.adapter.VPKnowledgeAdapter
import com.yukun.kotlinwanandroid.fragment.KnowledgeDatailFragment
import kotlinx.android.synthetic.main.activity_knowledge.*

class KnowledgeActivity : BaseActivity() {
    val split= mutableListOf<String>()
    var cid= mutableListOf<String>()
    var fragments= mutableListOf<KnowledgeDatailFragment>()
    override fun initLayout(): Int {
        return R.layout.activity_knowledge
    }

    override fun initUI() {
        val text = intent.getStringExtra("text")
        var id = intent.getStringExtra("cid")
        var s = text.split("/")
        var ids=id.split("/")
        split.addAll(s)
        split.removeAt(split.size-1)
        cid.addAll(ids)
        cid.removeAt(cid.size-1)
    }

    override fun initData() {
        for(index in 0 until split.size){
            val tab = tl_layout.newTab()
            tab.text=split[index]
            tl_layout.addTab(tab)
        }
        tl_layout.getTabAt(0)!!.select()
        for (index in 0 until split.size){
            val datailFragment = KnowledgeDatailFragment.getInstance()
            datailFragment!!.getData(cid[index].toInt())
            fragments.add(datailFragment)
        }

        var adapter= VPKnowledgeAdapter(supportFragmentManager,split,fragments)
        viewpager.adapter=adapter
        tl_layout.setupWithViewPager(viewpager)
    }

    override fun initListener() {
        iv_back.setOnClickListener {
            finish()
        }
    }
}
