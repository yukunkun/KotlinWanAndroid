package com.yukun.kotlinwanandroid.beans

import org.litepal.crud.LitePalSupport

/**
 * author: kun .
 * date:   On 2018/12/14
 */
 data class UserInfo (
         var id: Int = 0,
         var username: String? = null,
         var password: String? = null,
         var icon: Any? = null,
         var type: Int = 0,
         var collectIds: List<Int>? = null
)