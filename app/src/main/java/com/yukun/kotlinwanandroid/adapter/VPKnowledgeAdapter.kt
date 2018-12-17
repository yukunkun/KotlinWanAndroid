package com.yukun.kotlinwanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yukun.kotlinwanandroid.beans.WeChatBean

/**
 * author: kun .
 * date:   On 2018/12/17
 */
class VPKnowledgeAdapter (fm: FragmentManager?, private var tabs: List<String>, var fragments: List<Fragment>): FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun getPageTitle(position: Int): CharSequence = tabs[position]

}