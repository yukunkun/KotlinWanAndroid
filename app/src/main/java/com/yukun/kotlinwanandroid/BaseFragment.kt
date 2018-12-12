package com.yukun.kotlinwanandroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * author: kun .
 * date:   On 2018/12/12
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(initLayout(), null)
        initUI(inflate)
        initData()
        initListener()
        return inflate
    }

    abstract fun initLayout(): Int
    abstract fun initUI(inflate:View)
    abstract fun initData()
    abstract fun initListener()
}