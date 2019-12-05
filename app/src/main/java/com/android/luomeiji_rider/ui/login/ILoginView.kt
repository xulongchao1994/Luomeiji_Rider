package com.android.luomeiji_rider.ui.login

import com.android.luomeiji_rider.base.LBaseView
import com.android.luomeiji_rider.bean.LoginBean


interface ILoginView : LBaseView {
    fun loginsuccess(string: LoginBean)
    fun loginerror(string: String)
}