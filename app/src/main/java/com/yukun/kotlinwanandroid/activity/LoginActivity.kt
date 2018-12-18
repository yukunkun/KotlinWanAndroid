package com.yukun.kotlinwanandroid.activity

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.yukun.kotlinwanandroid.BaseActivity
import com.yukun.kotlinwanandroid.MyApp
import com.yukun.kotlinwanandroid.R
import com.yukun.kotlinwanandroid.beans.HomeListResponse
import com.yukun.kotlinwanandroid.beans.UserBean
import com.yukun.kotlinwanandroid.network.BaseCallBack
import com.yukun.kotlinwanandroid.network.RetrofitFactory
import com.yukun.kotlinwanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call

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
                    ToastUtils.show("请输入名字和密码")
                }
            }
            R.id.tv_register ->{
                et_relogin_password.visibility=View.VISIBLE
                if(!TextUtils.isEmpty(et_login_name.text.toString())&&!TextUtils.isEmpty(et_login_password.text)&&!TextUtils.isEmpty(et_relogin_password.text.toString())){
                    resigister(et_login_name.text.toString(),et_login_password.text.toString(),et_relogin_password.text.toString())
                }else{
                    ToastUtils.show("请输入名字和密码")
                }
            }
        }
    }

    //登录
    private fun login(name:String,password:String) {
        RetrofitFactory.getInstance().login(name,password,object :BaseCallBack<UserBean>{
            override fun onSuccess(data: UserBean) {
//                Log.i("=============s",data.toString())
                ToastUtils.show(this@LoginActivity,"登录成功")
                saveUserInfo(data)
            }

            override fun onBadRespone(msg: String) {
                ToastUtils.show(msg)
            }

            override fun onFailture(call: Call<HomeListResponse<UserBean>>?, t: Throwable?) {
                ToastUtils.show(t.toString())
            }
        })
    }

    private fun saveUserInfo(data: UserBean) {
        MyApp.getInstance()!!.userBean=data
        data.save()
        finish()
    }

    //注册
    private fun resigister(name: String, password: String, rePassword: String) {

        RetrofitFactory.getInstance().register(name,password,rePassword,object :BaseCallBack<UserBean>{
            override fun onSuccess(data: UserBean) {
            }

            override fun onBadRespone(msg: String) {
            }

            override fun onFailture(call: Call<HomeListResponse<UserBean>>?, t: Throwable?) {
            }

        })
    }
}
