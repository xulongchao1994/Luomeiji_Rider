package com.android.luomeiji_rider.ui.welcome

import android.content.Context
import com.android.luomeiji_rider.base.LBasePersenter

class WelcomePersenter(mView: IWelcomeView, context: Context) : LBasePersenter<IWelcomeView>(mView, context) {
    fun getlegalize(driverid: String) {
//        ApiData.getlegalize(driverid, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
//            override fun onSuccess(result: String?) {
//                Log.d("认证状态", result)
//                if (Utils.strinjson(result, "code") == "1") {
//                    mView!!.getlegalizesuccess(Utils.strinjson(result, "data"))
//                } else {
//                    mView.getlegalizeerror(Utils.strinjson(result, "message"))
//                }
//            }
//
//            override fun onFault(errorMsg: String?) {
//                mView.getlegalizeerror(errorMsg!!)
//            }
//        }))
    }
}