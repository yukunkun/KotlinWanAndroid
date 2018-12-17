package com.yukun.kotlinwanandroid.beans

/**
 * author: kun .
 * date:   On 2018/12/17
 */
class WeChatDetailBean(
        var offset: Int,
        var size: Int,
        var total: Int,
        var pageCount: Int,
        var curPage: Int,
        var over: Boolean,
        var datas: List<WeChatDetailBeans>?
){
    data class WeChatDetailBeans(
            var id: Int,
            var originId: Int,
            var title: String,
            var chapterId: Int,
            var chapterName: String?,
            var envelopePic: Any,
            var link: String,
            var author: String,
            var origin: Any,
            var publishTime: Long,
            var zan: Any,
            var desc: Any,
            var visible: Int,
            var niceDate: String,
            var courseId: Int,
            var collect: Boolean
    )
}

