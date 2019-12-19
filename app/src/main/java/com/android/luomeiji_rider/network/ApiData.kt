package com.android.luomeiji_rider.network

import io.reactivex.observers.DisposableObserver
import okhttp3.RequestBody
import okhttp3.ResponseBody


object ApiData {
    /**
     * 获取验证码
     */
    fun getcode(phone: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.getcode(phone)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 注册
     */
    fun signup(phone: String, VerificationCode: String, pwd: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.signup(phone, VerificationCode, pwd)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 登录
     */
    fun login(phone: String, pwd: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.login(phone, pwd)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     *认证
     */
    fun authentication(body: RequestBody, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.authentication(body)
        ApiFactify.fromSubscribe(observable, subscriber)
    }
    /**
     * 登录
     */
    fun getuserinfo(rider_id: String,  subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.getuserinfo(rider_id)
        ApiFactify.toSubscribe(observable, subscriber)
    }
    /**
     * 登录
     */
    fun gethomeorderlist(rider_id: String, order_type: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.gethomeorderlist(rider_id, order_type)
        ApiFactify.toSubscribe(observable, subscriber)
    }

}
