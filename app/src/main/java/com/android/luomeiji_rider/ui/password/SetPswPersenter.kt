package com.android.luomeiji_rider.UI.password

import android.content.Context
import android.util.Log
import com.android.luomeiji_rider.base.LBasePersenter

class SetPswPersenter(mView: ISetPswView, context: Context) :
    LBasePersenter<ISetPswView>(mView, context) {

    fun singuppsw(phoneNum: String, password: String, verPassword: String, code: String) {
        Log.d("设置密码：", phoneNum + "  " + password + "  " + verPassword + "  " + code)
//        ApiData.phonesigup(phoneNum, password, verPassword, code, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
//            override fun onSuccess(result: String?) {
//                var json = JSONObject(result)
//                var mseeage = json.get("message").toString()
//                var code = json.get("code").toString()
//                if (code == "1") {
//                    var signUpBean = GsonUtils.fromJson(result, SignUpBean::class.java)
//                    mView.singupsuccess(signUpBean)
//                } else {
//                    mView.signuperror(mseeage)
//                }
//            }
//
//            override fun onFault(errorMsg: String?) {
//                mView.signuperror(errorMsg!!)
//            }
//        }))

//        mApi!!.phonesigup(phoneNum, password, verPassword, code)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<SignUpBean> {
//                override fun onNext(t: SignUpBean) {
//                    Log.d("设置密码", t!!.toString())
//                    if (t!!.code == "1") {
//                        mView.singupsuccess(t)
//                    } else
//                        mView.signuperror(t.msg)
//                }
//
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    mDisposable = d
//                }
//
//
//                override fun onError(e: Throwable) {
//                    Log.d("设置密码", e!!.toString())
//                    mView.signuperror(e!!.message.toString())
//                }
//            })
    }

    fun findpsw(phoneNum: String, password: String, code: String) {
        Log.d("设置密码：", phoneNum + "  " + password + "  " + code)
//        ApiData.findpsw(phoneNum, password, code, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
//            override fun onSuccess(result: String?) {
//                var json = JSONObject(result)
//                var mseeage = json.get("message").toString()
//                var code = json.get("code").toString()
//                if (code == "1") {
//                    var signUpBean = GsonUtils.fromJson(result, SignUpBean::class.java)
//                    mView.singupsuccess(signUpBean)
//                } else {
//                    mView.signuperror(mseeage)
//                }
//            }
//
//            override fun onFault(errorMsg: String?) {
//                mView.signuperror(errorMsg!!)
//            }
//        }))

    }


}