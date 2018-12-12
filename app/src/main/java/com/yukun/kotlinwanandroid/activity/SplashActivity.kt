package com.yukun.kotlinwanandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.yukun.kotlinwanandroid.R
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

class SplashActivity : AppCompatActivity() {
    private var nextInt = 0
    var imageList= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initData()
        initUI()
        startAnimation()
    }

    private fun startAnimation() {
        //位移动画
        var translateAnimation=TranslateAnimation(0f,0f,400f,0f)
        translateAnimation.duration=2100
        tv_remind.animation=translateAnimation
        translateAnimation.startNow()

        //放大动画
        var scanAnimation= ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scanAnimation.duration=2000

        scanAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }
            override fun onAnimationEnd(animation: Animation?) {
                var intent=Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        iv_bg.animation=scanAnimation
        scanAnimation.startNow()
    }

    private fun initUI() {
        iv_bg.setBackgroundResource(imageList[nextInt])
    }

    private fun initData() {
        imageList.add(R.drawable.bg_1)
        imageList.add(R.drawable.bg_2)
        imageList.add(R.drawable.bg_3)
        imageList.add(R.drawable.bg_4)
        imageList.add(R.drawable.bg_5)
        imageList.add(R.drawable.bg_6)
        imageList.add(R.drawable.bg_7)
        imageList.add(R.drawable.bg_8)
        var random=Random()
        nextInt = random.nextInt(8)
    }
}

