package com.yukun.kotlinwanandroid

import android.app.Application

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class MyApp : Application() {

    // companion相当于java中的static,kotlin不能直接访问，加了companion就能访问了
    companion object {
        var myApp : MyApp ?= null

        fun getInstance() = myApp

        //功能同上方法
        fun getMyApplication():MyApp{
            return myApp!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        myApp=MyApp()
    }

}