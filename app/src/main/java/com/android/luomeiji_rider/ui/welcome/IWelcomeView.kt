package com.android.luomeiji_rider.ui.welcome

import com.android.luomeiji_rider.base.LBaseView


interface IWelcomeView : LBaseView {
    //获取当前认证状态
    fun getlegalizesuccess(string: String)

    fun getlegalizeerror(string: String)
}