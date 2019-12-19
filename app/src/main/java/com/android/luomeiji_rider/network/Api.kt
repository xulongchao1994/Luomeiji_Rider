package com.android.luomeiji_rider.network

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Api {
    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("rider/sendVerificationCode")
    fun getcode(@Field("phone") phone: String): io.reactivex.Observable<ResponseBody>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("rider/register")
    fun signup(@Field("phone") phone: String,
               @Field("VerificationCode") VerificationCode: String, @Field("pwd") pwd: String): Observable<ResponseBody>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("rider/login")
    fun login(@Field("phone") phone: String, @Field("pwd") pwd: String): Observable<ResponseBody>

    /**
     * 认证
     */
    @POST("rider/authentication")
    fun authentication(@Body body: RequestBody): Observable<ResponseBody>

    /**
     * 认证成功后显示个人中心
     */
    @FormUrlEncoded
    @POST("getPersonalCenter")
    fun getuserinfo(@Field("rider_id") rider_id: String): Observable<ResponseBody>

    /**
     * 显示首页订单，根据骑手id，订单类型显示订单
     */
    @FormUrlEncoded
    @POST("rider/order/getListOrder")
    fun gethomeorderlist(@Field("rider_id") rider_id: String, @Field("order_type") order_type: String): Observable<ResponseBody>
}