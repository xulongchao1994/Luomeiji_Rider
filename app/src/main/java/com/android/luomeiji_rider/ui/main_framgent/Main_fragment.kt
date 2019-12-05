package com.android.luomeiji_rider.ui.main_framgent

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseFragment
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_main.*

class Main_fragment : LBaseFragment<Main_fragmentPersenter>(), IMain_Fragmentview {
    override fun initPersenter() {
        mPersenter = Main_fragmentPersenter(this, activity!!.baseContext)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_main
    }

    var orderlist = arrayListOf<String>()
    var ordertype = ""
    override fun initViews(view: View) {
        ordertype = arguments!!.getString("ordertype").toString()
        main_fragment_srl.setOnRefreshListener {
            main_fragment_srl.isRefreshing = false
        }
        for (i in 0..15) {
            orderlist.add(ordertype + i.toString())
        }
        var linearLayoutManager = LinearLayoutManager(activity!!)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        main_fragment_recycler.layoutManager = linearLayoutManager
        var adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_main_fragment_item) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.order_item_type, item!!)
            }
        }
        main_fragment_recycler.adapter = adapter
        adapter.setNewData(orderlist)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}