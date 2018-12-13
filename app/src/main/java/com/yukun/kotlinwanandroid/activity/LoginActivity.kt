package com.yukun.kotlinwanandroid.activity

import android.opengl.Visibility
import android.text.Editable
import android.text.TextUtils
import android.view.View
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {


    override fun initLayout(): Int {
        return R.layout.activity_login
    }

    override fun initUI() {
    }

    override fun initData() {
    }

    override fun initListener() {
        tv_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        iv_back.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_back -> finish()

            R.id.tv_login ->{
                if(!TextUtils.isEmpty(et_login_name.text.toString())&&!TextUtils.isEmpty(et_login_password.text)){
                    login(et_login_name.text.toString(),et_login_password.text.toString())
                }else{
//                    ToastUtils.show("请输入名字和密码")
                }
            }
            R.id.tv_register ->{
                et_relogin_password.visibility=View.INVISIBLE
                if(!TextUtils.isEmpty(et_login_name.text.toString())&&!TextUtils.isEmpty(et_login_password.text)&&!TextUtils.isEmpty(et_relogin_password.text.toString())){
                    resigister(et_login_name.text.toString(),et_login_password.text.toString(),et_relogin_password.text.toString())
                }else{
//                    ToastUtils.show("请输入名字和密码")
                }
            }
        }
    }

    //登录
    private fun login(name:String,password:String) {

    }
    //注册
    private fun realRegister(name: String, password: String) {

    }

    private fun resigister(name: String, password: String, rePassword: String) {
        if(!password.equals(rePassword)){
            ToastUtils.show("两次的密码不正确")
            return
        }else{
            realRegister(name,password)
        }
    }




}
