package com.yukun.kotlinwanandroid

import android.app.Application
import com.yukun.kotlinwanandroid.beans.UserBean
import com.yukun.kotlinwanandroid.beans.UserInfo
import org.litepal.LitePal

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class MyApp : Application() {
    var userBean : UserBean?=null
    // companion相当于java中的static,kotlin不能直接访问，加了companion就能访问了
    companion object {
        var myApp : MyApp ?= null
        fun getInstance() = myApp
    }

    override fun onCreate() {
        super.onCreate()
        myApp=this
        LitePal.initialize(this)
    }

    fun getMyApplication():MyApp{
        return myApp!!
    }
}