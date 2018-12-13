package com.yukun.kotlinwanandroid.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import kotlinx.android.synthetic.main.activity_mine.*

class MineActivity : BaseActivity(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initLayout(): Int {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
      return R.layout.activity_mine
    }

    override fun initUI() {

    }

    override fun initData() {

    }

    override fun initListener() {
        rl_collect.setOnClickListener(this)
        rl_adoutus.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        tv_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.iv_back -> finish()

            R.id.rl_collect ->{

            }
            R.id.rl_adoutus ->{

            }

            R.id.tv_login ->{
                if(true){
                    var intent=Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }else{

                }
            }
        }
    }


}
