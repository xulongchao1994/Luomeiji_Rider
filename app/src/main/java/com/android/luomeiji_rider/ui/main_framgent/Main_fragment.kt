package com.android.luomeiji_rider.ui.main_framgent

import android.view.View
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class Main_fragment : LBaseFragment<Main_fragmentPersenter>(), IMain_Fragmentview {
    override fun initPersenter() {
        mPersenter = Main_fragmentPersenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_main
    }

    override fun initViews(view: View) {
        main_fragment_srl.setOnRefreshListener {
            main_fragment_srl.isRefreshing = false
        }

    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}