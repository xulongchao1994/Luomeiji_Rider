package com.android.luomeiji_rider.ui.legalize

import android.content.Context
import android.util.Log
import com.android.luomeiji_rider.base.LBasePersenter
import com.android.luomeiji_rider.network.ApiData
import com.android.luomeiji_rider.network.OnSuccessAndFaultListener
import com.android.luomeiji_rider.network.OnSuccessAndFaultSub
import com.android.luomeiji_rider.tools.RUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CarLegalizePersenter(mView: ICarLegalizeView, context: Context) :
        LBasePersenter<ICarLegalizeView>(mView, context) {
    fun carlegalize(name: String, idCard: String, idCardFront: File, idCardBack: File, holding_ID_card: File, rider_id: String) {
        var bodys = MultipartBody.Builder()
                .addFormDataPart(
                        "name", name
                )
                .addFormDataPart(
                        "idCard", idCard
                )
                .addFormDataPart(
                        "idCardFront", idCardFront.name, RequestBody.create(MediaType.parse("image/*"), idCardFront)
                )
                .addFormDataPart(
                        "idCardBack", idCardBack.name, RequestBody.create(MediaType.parse("image/*"), idCardBack)
                )
                .addFormDataPart(
                        "holding_ID_card", holding_ID_card.name, RequestBody.create(MediaType.parse("image/*"), holding_ID_card)
                )
                .addFormDataPart(
                        "rider_id", rider_id
                ).build()
        ApiData.authentication(bodys, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                Log.d("认证成功", result)
                if (RUtils.strinjson(result, "code") == "1") {
                    mView.carlegalizesuccess(RUtils.strinjson(result, "message"))
                } else {
                    mView.carlegalizeerror(RUtils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                Log.d("认证失败", errorMsg)
                mView.carlegalizeerror(errorMsg!!)
            }
        }))
    }
}