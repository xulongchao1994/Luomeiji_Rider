package com.android.luomeiji_rider.UI.password

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.bean.SignUpBean
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.ui.main.MainActivity
import com.vondear.rxtool.RxKeyboardTool
import com.vondear.rxtool.view.RxToast
import kotlinx.android.synthetic.main.activity_setpsw.*

class SetPswActivity : LBaseAppCompatActivity<SetPswPersenter>(), ISetPswView {
    override fun singupsuccess(signUpBean: SignUpBean) {
        RxKeyboardTool.hideKeyboard(this, settingpsw_onepsw)
        RxKeyboardTool.hideKeyboard(this, settingpsw_twopsw)
        editor!!.putBoolean("islogin", true)
        editor!!.putString("username", signUpBean.data.phoneNum)
        editor!!.putString("password", settingpsw_twopsw.text.toString())
        editor!!.putString("userId", signUpBean.data.userId)
        editor!!.putString("phoneNum", signUpBean.data.phoneNum)
        editor!!.putString("avatar", signUpBean.data.avatar)
        editor!!.putString("nickName", signUpBean.data.nickName)
        editor!!.commit()
        UserData_Java.islogin = true
        UserData_Java.userid = signUpBean.data.userId
        UserData_Java.phoneNum = signUpBean.data.phoneNum
        UserData_Java.avatar = signUpBean.data.avatar
        UserData_Java.nickName = signUpBean.data.nickName
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun signuperror(string: String) {
        RxToast.normal(string)
    }

    override fun initlayout(): Int {
        return R.layout.activity_setpsw
    }

    var phonenumber = ""
    var phonecode = ""

    var editor: SharedPreferences.Editor? = null
    var sp: SharedPreferences? = null
    var type = ""
    override fun initView() {
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        editor = sp!!.edit()
        phonenumber = intent.getStringExtra("phonenumber")
        phonecode = intent.getStringExtra("phonecode")
        settingpsw_ok_bt.setOnClickListener {
            psworpsw()
        }
//        settingpsw_twopsw.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                if (settingpsw_twopsw.text.toString() != settingpsw_onepsw.text.toString()) {
//                    settingpsw_not_layout.visibility = View.VISIBLE
////                    MyToast.showMsg("两次密码不一致")
//                } else {
//                    settingpsw_not_layout.visibility = View.GONE
//                }
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//        })
    }

    private fun psworpsw() {
        var onepsw = settingpsw_onepsw.text.toString()
        var twopsw = settingpsw_twopsw.text.toString()
        if (onepsw == "") {
            RxToast.normal("请输入密码")
            return
        }
        if (twopsw == "") {
            RxToast.normal("请确认密码")
            return
        }
        if (twopsw != onepsw) {
            RxToast.normal("两次密码不一致")
            return
        }
        when (type) {
            "1" -> {
                mPersenter!!.singuppsw(phonenumber, settingpsw_onepsw.text.toString(), settingpsw_twopsw.text.toString(), phonecode)
            }
            "2" -> {
                mPersenter!!.findpsw(phonenumber, settingpsw_onepsw.text.toString(), phonecode)
            }
        }
    }

    override fun initPersenter() {
        mPersenter = SetPswPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}