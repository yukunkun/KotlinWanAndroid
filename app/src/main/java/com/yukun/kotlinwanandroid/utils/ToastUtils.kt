package com.yukun.kotlinwanandroid.utils

import android.widget.Toast
import com.yukun.kotlinwanandroid.MyApp

/**
 * author: kun .
 * date:   On 2018/12/13
 */
class ToastUtils {
    companion object {
        fun show(msg:String){
            Toast.makeText(MyApp.getInstance()!!.getMyApplication(),msg,Toast.LENGTH_SHORT).show()
        }
    }
}