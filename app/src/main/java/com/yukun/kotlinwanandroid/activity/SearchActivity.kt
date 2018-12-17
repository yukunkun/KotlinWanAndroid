package com.yukun.kotlinwanandroid.activity

import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R

class SearchActivity : BaseActivity() {
    var key=null
    override fun initLayout(): Int {
       return R.layout.activity_search
    }


    override fun initUI() {
       key = intent.getStringExtra("key") as Nothing?
    }

    override fun initData() {
    }

    override fun initListener() {
    }


}
