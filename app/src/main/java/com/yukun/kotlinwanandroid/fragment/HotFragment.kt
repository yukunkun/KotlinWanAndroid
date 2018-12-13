package com.yukun.kotlinwanandroid.fragment

import android.os.Bundle
import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class HotFragment(): BaseFragment() {

    companion object {
        var hotFragment : HotFragment ?= null

        fun getInstance():HotFragment{
            hotFragment=HotFragment()
            return hotFragment!!
        }
    }

    override fun initUI(inflate: View, savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun initLayout(): Int {
        return R.layout.hot_fragment
    }

}