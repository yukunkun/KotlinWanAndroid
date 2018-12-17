package com.yukun.kotlinwanandroid.beans

import java.io.Serializable

/**
 * author: kun .
 * date:   On 2018/12/14
 */
data class KnowledgeBean(
             var id: Int,
                var name: String,
                var courseId: Int,
                var parentChapterId: Int,
                var order: Int,
                var visible: Int,
                var children:List<Children>?
        ) : Serializable {
            data class Children(
                    var id: Int,
                    var name: String,
                    var courseId: Int,
                    var parentChapterId: Int,
                    var order: Int,
                    var visible: Int,
                    var children: List<Children>?
            ) : Serializable
}