package com.android.luomeiji_rider.ui.login

import android.content.Context
import android.util.Log
import com.android.luomeiji_rider.base.LBasePersenter
import com.android.luomeiji_rider.bean.LoginBean
import com.android.luomeiji_rider.network.ApiData
import com.android.luomeiji_rider.network.OnSuccessAndFaultListener
import com.android.luomeiji_rider.network.OnSuccessAndFaultSub
import com.android.luomeiji_rider.tools.GsonUtils
import com.android.luomeiji_rider.tools.RUtils

class LoginPersenter(mView: ILoginView, context: Context) :
        LBasePersenter<ILoginView>(mView, context) {
    fun logining(name: String, psw: String) {
        Log.d("登录：", name + "  " + psw)
        ApiData.login(name, psw, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                Log.d("登录", result)
                if (RUtils.strinjson(result, "code") == "1") {
                    var loginbean = GsonUtils.fromJson(result, LoginBean::class.java)
                    mView.loginsuccess(loginbean)
                } else {
                    mView.loginerror(RUtils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.loginerror(errorMsg!!)
            }
        }))
    }
}