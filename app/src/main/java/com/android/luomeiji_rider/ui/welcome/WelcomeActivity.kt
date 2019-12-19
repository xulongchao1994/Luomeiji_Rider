package com.android.luomeiji_rider.ui.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.tools.LActivityTool
import com.android.luomeiji_rider.ui.legalize.CarLegalizeActivity
import com.android.luomeiji_rider.ui.login.LoginActivity
import com.android.luomeiji_rider.ui.main.MainActivity

class WelcomeActivity : LBaseAppCompatActivity<WelcomePersenter>(), IWelcomeView {
    override fun getlegalizesuccess(string: String) {
//        UserData_Java.driverReviewState = string
//        if (userdata_java.driverreviewstate == "2" || userdata_java.driverreviewstate == "3") {
//            startactivity(intent(this, carowneractivity::class.java))
//            finish()
//        } else if (userdata_java.driverreviewstate == "1") {
//            startactivity(intent(this, mainactivity::class.java))
//        } else if (userdata_java.driverreviewstate == "0") {//正在审核
//        }
        finish()
    }

    override fun getlegalizeerror(string: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_welcome
    }

    private var sp: SharedPreferences? = null
    var isfirst = false
    override fun initView() {
        LActivityTool.addActivity(this)
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        UserData_Java.islogin = sp!!.getBoolean("islogin", false)
        UserData_Java.isbingbank = sp!!.getBoolean("isbindbank", false)
        UserData_Java.islegalize = sp!!.getString("renzheng", "")
        isfirst = sp!!.getBoolean("isfirst", false)
        if (UserData_Java.islogin) {
//            mPersenter!!.getlegalize(UserData_Java.driveruserid)
            if (UserData_Java.islegalize == "1") {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, CarLegalizeActivity::class.java))
                finish()
            }

        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun initPersenter() {
        mPersenter = WelcomePersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}