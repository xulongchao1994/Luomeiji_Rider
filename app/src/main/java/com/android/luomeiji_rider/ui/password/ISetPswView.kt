package com.android.luomeiji_rider.UI.password

import com.android.luomeiji_rider.base.LBaseView
import com.android.luomeiji_rider.bean.SignUpBean


interface ISetPswView : LBaseView {
    fun singupsuccess(signUpBean: SignUpBean)
    fun signuperror(string: String)
}