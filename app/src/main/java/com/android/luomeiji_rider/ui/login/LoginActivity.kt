package com.android.luomeiji_rider.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.android.luomeiji_driver.UI.signup.SignupActivity
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.bean.LoginBean
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.ui.main.MainActivity
import com.vondear.rxtool.RxKeyboardTool
import com.vondear.rxtool.view.RxToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : LBaseAppCompatActivity<LoginPersenter>(), ILoginView {
    override fun loginsuccess(string: LoginBean) {
        RxKeyboardTool.hideKeyboard(this, login_name_et)
        RxKeyboardTool.hideKeyboard(this, login_psw_et)
        editor!!.putBoolean("islogin", true)
        editor!!.putString("username", login_name_et.text.toString())
        editor!!.putString("password", login_psw_et.text.toString())
        editor!!.putString("userId", string.data.userId)
        editor!!.putString("phoneNum", string.data.phoneNum)
        editor!!.putString("avatar", string.data.avatar)
        editor!!.putString("nickName", string.data.nickName)
        editor!!.commit()
        UserData_Java.islogin = true
        UserData_Java.userid = string.data.userId
        UserData_Java.phoneNum = string.data.phoneNum
        UserData_Java.avatar = string.data.avatar
        UserData_Java.nickName = string.data.nickName
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun loginerror(string: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_login
    }

    var editor: SharedPreferences.Editor? = null
    var sp: SharedPreferences? = null
    override fun initView() {

        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        editor = sp!!.edit()
        login_singin.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java).putExtra("type", 1))
        }
        login_forgetpsw.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java).putExtra("type", 2))
        }
        login_login_bt.setOnClickListener {
            var name = login_name_et.text.toString()
            var psw = login_psw_et.text.toString()
            if (name == "") {
                RxToast.normal("请输入手机号")
                return@setOnClickListener
            }
            if (psw == "") {
                RxToast.normal("请输入密码")
                return@setOnClickListener
            }
            mPersenter!!.logining(name, psw)
        }
    }

    override fun initPersenter() {
        mPersenter = LoginPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }

}