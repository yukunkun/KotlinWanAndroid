package com.yukun.kotlinwanandroid.beans

/**
 * author: kun .
 * date:   On 2018/12/14
 */
class WeChatBean(
        var children: List<KnowledgeBean.Children>?,
        var courseId: Int,
        var id: Int,
        var name: String,
        var order: Int,
        var parentChapterId: Int,
        var userControlSetTop:Boolean,
        var visible: Int
)