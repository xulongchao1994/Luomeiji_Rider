package com.android.luomeiji_rider.ui.orderlist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_orderlist.*

class OrderListActivity:LBaseAppCompatActivity<OrderListPersenter>(),IOrderListView {
    override fun initlayout(): Int {
        return R.layout.activity_orderlist
    }
    var orderlistdata = arrayListOf<String>()
    override fun initView() {
        orderlist_back.setOnClickListener { finish() }
        var linearlayotumanager = LinearLayoutManager(this)
        linearlayotumanager.orientation = RecyclerView.VERTICAL
        orderlist_rv.layoutManager = linearlayotumanager
        var adapter= object :BaseQuickAdapter<String,BaseViewHolder>(R.layout.adapter_orderlist_item){
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.adapter_orderlist_item_ordernuber,item!!)
            }
        }
        orderlist_rv.adapter = adapter
        for (i in 0..15){
            orderlistdata.add(i.toString())
        }
        adapter.setNewData(orderlistdata)
    }

    override fun initPersenter() {
        mPersenter = OrderListPersenter(this,this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}