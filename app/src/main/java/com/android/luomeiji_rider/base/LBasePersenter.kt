package com.android.luomeiji_rider.base

import android.content.Context
import com.android.luomeiji_rider.network.Api
import com.android.luomeiji_rider.network.ApiFactify
import io.reactivex.disposables.Disposable

abstract class LBasePersenter<V : LBaseView>(var mView: V, var mContext: Context) {
    protected var mApi: Api? = null
    protected var mDisposable: Disposable? = null

    init {
        if (mApi == null) {
            mApi = ApiFactify.getInstance()
        }
    }

    /**
     * 取消网络请求(这里是有RxJava,即为取消订阅结果)
     */

    fun cannelRequest() {
//        if (mSubscription != null)
//            mSubscription!!.unsubscribe()
        if (mDisposable != null && !mDisposable!!.isDisposed)
            mDisposable!!.dispose()
    }
}