package com.yukun.kotlinwanandroid.fragment

import android.view.View
import com.yukun.kotlinwanandroid.BaseFragment
import com.yukun.kotlinwanandroid.R

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class IndexFragment(): BaseFragment() {

    companion object {
        var indexFragment : IndexFragment ?= null

        fun getInstance():IndexFragment{
            indexFragment=IndexFragment()
            return indexFragment!!
        }
    }
    override fun initUI(inflate: View) {
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun initLayout(): Int {
        return R.layout.index_fragment
    }

}