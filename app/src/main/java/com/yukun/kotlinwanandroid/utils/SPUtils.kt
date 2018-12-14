package com.yukun.kotlinwanandroid.utils

import android.content.Context
import android.content.SharedPreferences
import com.yukun.kotlinwanandroid.MyApp

/**
 * author: kun .
 * date:   On 2018/12/14
 */
class SPUtils {
    var preferences:SharedPreferences?=null
    companion object {
        var spUtils:SPUtils?=null
        fun getInstance():SPUtils{
            if(spUtils==null){
                spUtils= SPUtils()
            }
            return spUtils!!
        }
    }

    constructor(){
        initSP()
    }

    fun initSP() {
        preferences = MyApp.getInstance()!!.getMyApplication().getSharedPreferences(
                MyApp.getInstance()!!.getMyApplication().packageName + /*Constant.SHARED_NAME*/"_preferences",
                Context.MODE_PRIVATE
        )
    }

    fun clear() {
        preferences!!.edit().clear().apply()
    }

    //with 语句。。。
    public fun <U> putPreference(name: String, value: U) = with(preferences!!.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }

    public fun <U> findPreference(name: String, default: U): U = with(preferences!!) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }
}