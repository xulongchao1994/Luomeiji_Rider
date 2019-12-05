package com.android.luomeiji_rider.ui.signup

import com.android.luomeiji_rider.base.LBaseView


interface ISignUpView : LBaseView {
    fun getcodesuccess(code: String)
    fun getcodeerror(error: String)
}