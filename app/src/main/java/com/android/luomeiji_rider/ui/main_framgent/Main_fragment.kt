package com.android.luomeiji_rider.ui.main_framgent

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseFragment
import com.android.luomeiji_rider.bean.Home_OrderBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.vondear.rxtool.view.RxToast
import kotlinx.android.synthetic.main.fragment_main.*

class Main_fragment : LBaseFragment<Main_fragmentPersenter>(), IMain_Fragmentview {
    override fun initPersenter() {
        mPersenter = Main_fragmentPersenter(this, activity!!.baseContext)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_main
    }

    var orderlist = arrayListOf<Home_OrderBean.DataBean>()
    var ordertype = ""
    var adapter: BaseQuickAdapter<Home_OrderBean.DataBean, BaseViewHolder>? = null
    var page = 1
    override fun initViews(view: View) {
        ordertype = arguments!!.getString("ordertype").toString()
        main_fragment_srl.setOnRefreshListener {
            page = 1
            mPersenter!!.getdata(ordertype)
        }
        var linearLayoutManager = LinearLayoutManager(activity!!)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        main_fragment_recycler.layoutManager = linearLayoutManager
        adapter = object : BaseQuickAdapter<Home_OrderBean.DataBean, BaseViewHolder>(R.layout.adapter_main_fragment_item) {
            override fun convert(helper: BaseViewHolder?, item: Home_OrderBean.DataBean?) {
                helper!!.setText(R.id.order_getordername, item!!.business_address)
                        .setText(R.id.order_getorderaddress, item!!.detailed_address)
                        .setText(R.id.order_postorderaddress, item!!.customer_detailed_address)
                        .setText(R.id.order_postordername, item!!.customer_address)
            }
        }
        main_fragment_recycler.adapter = adapter
    }

    override fun getdatasuccess(homeOrderbean: Home_OrderBean) {
        if (page == 1) {
            orderlist.clear()
            adapter!!.setNewData(homeOrderbean.data)
            main_fragment_srl.isRefreshing = false
        } else {
            adapter!!.addData(homeOrderbean.data)
        }
        if (homeOrderbean.data.size < 10) {
            adapter!!.loadMoreEnd()
        } else {
            adapter!!.loadMoreComplete()
        }

        orderlist.addAll(homeOrderbean.data)
    }

    override fun getdataerror(string: String) {
        RxToast.showToast(string)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}