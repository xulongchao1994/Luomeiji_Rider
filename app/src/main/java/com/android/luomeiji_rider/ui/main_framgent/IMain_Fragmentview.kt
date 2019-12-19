package com.android.luomeiji_rider.ui.main_framgent

import com.android.luomeiji_rider.base.LBaseView
import com.android.luomeiji_rider.bean.Home_OrderBean

interface IMain_Fragmentview : LBaseView {
    fun getdatasuccess(homeOrderbean: Home_OrderBean)
    fun getdataerror(string: String)
}