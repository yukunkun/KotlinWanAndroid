package com.yukun.kotlinwanandroid.activity

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_splash.*

class DetailActivity : BaseActivity(), View.OnClickListener {

    var mUrl:String?=null
    override fun initLayout(): Int {
        return R.layout.activity_detail
    }

    override fun initUI() {
        mUrl = intent.getStringExtra("url")
        var title=intent.getStringExtra("title")
        val webSettings = webview.settings
        webSettings.javaScriptEnabled=true
        webview.loadUrl(mUrl)
        tv_title.text=title
        tv_title.isSelected=true
    }

    override fun initData() {

    }

    override fun initListener() {
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                progressbar.setVisibility(View.GONE)
            }
        }

        iv_back.setOnClickListener(this)
        iv_share.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_back -> finish()
            R.id.iv_share -> {
                //share
                val textIntent = Intent(Intent.ACTION_SEND)
                textIntent.type = "text/plain"
                textIntent.putExtra(Intent.EXTRA_TEXT, mUrl)
                startActivity(Intent.createChooser(textIntent, "分享"))
            }
        }
    }

    override
    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (webview.canGoBack()) {
            webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
