package com.android.luomeiji_rider.ui.withdraw

import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.tools.LActivityTool


class WithdrawActivity : LBaseAppCompatActivity<WithdrawPersenter>(), IWithdrawView {
    override fun initlayout(): Int {
        return R.layout.activity_withdraw
    }

    override fun initView() {
        LActivityTool.addActivity(this)
    }

    override fun initPersenter() {
        mPersenter = WithdrawPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}