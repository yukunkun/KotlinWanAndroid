package com.yukun.kotlinwanandroid.activity

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.fragment.HotFragment
import com.yukun.kotlinwanandroid.fragment.IndexFragment
import com.yukun.kotlinwanandroid.fragment.KnowledgeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : BaseActivity() {
    var beginTransaction : FragmentTransaction ?=null
    var mFragments= mutableListOf<Fragment>()
    var lastpos : Int = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initLayout(): Int {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
        return R.layout.activity_main
    }

    override fun initUI() {
        mFragments.add(IndexFragment.getInstance())
        mFragments.add(KnowledgeFragment.getInstance())
        mFragments.add(HotFragment.getInstance())
        beginTransaction = supportFragmentManager.beginTransaction()!!
        // beginTransaction !! 可以为空
        val fragmentTransaction = beginTransaction!!.add(R.id.fl_layout, mFragments[0])
        fragmentTransaction.commit()
        //默认选中
        (rg.getChildAt(0) as RadioButton).isChecked=true
    }

    override fun initData() {

    }

    override fun initListener() {
        rg.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                println(checkedId)
                // checkedId 为 1 2 3
                if(checkedId in 1..4 ){
                    (rg.getChildAt(checkedId-1) as RadioButton).isChecked=true
                    showFragment(checkedId-1)
                }
//                when(checkedId){
//                   1 -> {
//                        (rg.getChildAt(checkedId) as RadioButton).isChecked=true
//                   }
//                   2 -> {
//                       (rg.getChildAt(checkedId) as RadioButton).isChecked=true
//                   }
//                   3 ->{
//                       (rg.getChildAt(checkedId) as RadioButton).isChecked=true
//                   }
//                }
            }
        })
    }

    fun showFragment(pos : Int){
        val fragment = mFragments[pos]
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.hide(mFragments[lastpos])
        if(fragment.isAdded){
            fragmentTransaction.show(mFragments[pos])
        }else{
            fragmentTransaction.add(R.id.fl_layout,fragment)
        }
        fragmentTransaction.commit();
        lastpos=pos
    }
}
