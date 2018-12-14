package com.yukun.kotlinwanandroid.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Html
import android.view.*
import android.widget.TextView
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.utils.ScreenUtils
import kotlinx.android.synthetic.main.center_dialog.*

/**
 * author: kun .
 * date:   On 2018/12/14
 */
class AboutUsDialog : DialogFragment() {

    companion object {
        var mAboutUs:AboutUsDialog?=null
        fun getInstance() = AboutUsDialog()
    }

    var htmlText = "<body class=\"ke-content\"><p style=\"font-size:14px;font-family:&quot;margin-left:5px;background:#FFFFFF;\">QQ交流群：591683946</p><p style=\"font-size:14px;font-family:&quot;margin-left:5px;background:#FFFFFF;\">对本站的建议：<a target=\"_blank\" href=\"https://github.com/hongyangAndroid/wanandroid\" data-ke-src=\"https://github.com/hongyangAndroid/wanandroid\">我要反馈</a></p><p style=\"font-size:14px;font-family:&quot;margin-left:5px;background:#FFFFFF;\">最近更新：最近加了对分类的检索，尝试搜索“优化”即可发现；更换了网站主题。</p><p style=\"font-size:14px;font-family:&quot;margin-left:5px;background:#FFFFFF;text-align:right;\">欢迎收藏本站。</p></body>"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)//去掉标题
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))//设置背景透明
        val inflate = inflater.inflate(R.layout.center_dialog, null)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_msg.text= Html.fromHtml(htmlText)
    }


    override fun onStart() {
        super.onStart()
        val window = dialog.window
        if(window!=null){
            val height = ScreenUtils.instance().getHeight(context)
            val width = ScreenUtils.instance().getWidth(context)
            val params = window.attributes
//            params.dimAmount =0f;
            //修改gravity
            params.gravity = Gravity.CENTER
            params.width = width * 4 / 5
            params.height = height * 3 / 7
            window.attributes = params
        }
    }
}