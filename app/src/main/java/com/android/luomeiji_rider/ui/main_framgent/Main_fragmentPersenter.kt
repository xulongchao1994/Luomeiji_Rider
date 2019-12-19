package com.android.luomeiji_rider.ui.main_framgent

import android.content.Context
import com.android.luomeiji_rider.base.LBasePersenter
import com.android.luomeiji_rider.bean.Home_OrderBean
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.network.ApiData
import com.android.luomeiji_rider.network.OnSuccessAndFaultListener
import com.android.luomeiji_rider.network.OnSuccessAndFaultSub
import com.android.luomeiji_rider.tools.GsonUtils

class Main_fragmentPersenter(mFragmentview: IMain_Fragmentview, context: Context) : LBasePersenter<IMain_Fragmentview>(mFragmentview, context) {
    fun getdata(ordertype: String) {
        ApiData.gethomeorderlist(UserData_Java.userid, ordertype, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                var homeOrderbean = GsonUtils.fromJson(result, Home_OrderBean::class.java)
                mView.getdatasuccess(homeOrderbean)
            }

            override fun onFault(errorMsg: String?) {
                mView.getdataerror(errorMsg!!)
            }
        }))
    }
}