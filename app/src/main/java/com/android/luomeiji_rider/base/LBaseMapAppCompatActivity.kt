package com.android.luomeiji_driver.Base

import android.app.Activity
import android.os.Bundle
import com.android.luomeiji_rider.base.LBasePersenter
import com.android.luomeiji_rider.base.LoadingDialog
import com.android.luomeiji_rider.tools.UiDensity
import com.vondear.rxtool.RxActivityTool

abstract class LBaseMapAppCompatActivity<P : LBasePersenter<*>> : Activity() {
    
    protected var mPersenter: P? = null
    @JvmField
    protected var mLoading: LoadingDialog? = null

    protected abstract fun initlayout(): Int
    protected abstract fun initView()
    protected abstract fun initPersenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoading = LoadingDialog(this)
        mLoading!!.setCanceledOnTouchOutside(false)
        UiDensity.setDefault(this)
        RxActivityTool.addActivity(this)
        setContentView(initlayout())
        initPersenter()
        initView()
    }

    override fun onStop() {
        super.onStop()
        if (mPersenter != null) {
            mPersenter!!.cannelRequest()
        }
        if (mLoading != null) {
            mLoading!!.dismiss()
            mLoading = null
        }
//        mPersenter = null
    }
}