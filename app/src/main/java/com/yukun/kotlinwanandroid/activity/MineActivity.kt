package com.yukun.kotlinwanandroid.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.MyApp
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.beans.UserBean
import com.yukun.kotlinwanandroid.dialog.AboutUsDialog
import com.yukun.kotlinwanandroid.utils.Preference
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_mine.*
import org.litepal.LitePal

class MineActivity : BaseActivity(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initLayout(): Int {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
      return R.layout.activity_mine
    }

    override fun initUI() {
        if(MyApp.getInstance()!!.userBean!=null){
            tv_name.text= MyApp.getInstance()!!.userBean!!.username
            tv_login.text="退出登录"
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        rl_collect.setOnClickListener(this)
        rl_adoutus.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        tv_login.setOnClickListener(this)
    }

    override fun onRestart() {
        super.onRestart()
        if(MyApp.getInstance()!!.userBean!=null){
            tv_name.text= MyApp.getInstance()!!.userBean!!.username
            tv_login.text="退出登录"
        }
    }
    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.iv_back -> finish()

            R.id.rl_collect ->{
                if(tv_login.text == "退出登录"){
                    var intent=Intent(this,CollectActivity::class.java)
                    startActivity(intent)
                }else{
                    ToastUtils.show("请先登录")
                    var intent=Intent(this,LoginActivity::class.java)
                    startActivity(intent)

                }
            }
            R.id.rl_adoutus ->{
                val instance = AboutUsDialog.getInstance()
                instance.show(supportFragmentManager,"")
            }

            R.id.tv_login ->{
                if(tv_login.text == "退出登录"){
                    LitePal.deleteAll(UserBean::class.java)
                    MyApp.getInstance()!!.userBean=null
                    Preference.clear()
                    finish()
                }else{
                    var intent=Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


}
