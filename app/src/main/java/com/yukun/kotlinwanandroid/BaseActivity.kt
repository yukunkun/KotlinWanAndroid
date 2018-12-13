package com.yukun.kotlinwanandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * author: kun .
 * date:   On 2018/12/12
 */
abstract class BaseActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())
        initUI()
        initData()
        initListener()
    }

    abstract fun initLayout(): Int
    abstract fun initUI()
    abstract fun initData()
    abstract fun initListener()

}