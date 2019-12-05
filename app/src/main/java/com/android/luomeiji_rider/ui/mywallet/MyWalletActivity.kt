package com.android.luomeiji_rider.ui.mywallet

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.base.LBaseViewHolder
import com.android.luomeiji_rider.bean.MyWalletBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_mywallet.*

class MyWalletActivity : LBaseAppCompatActivity<MyWalletPersenter>(), IMyWalletView {
    override fun initlayout(): Int {
        return R.layout.activity_mywallet
    }

    var datalist = arrayListOf<MyWalletBean>()
    override fun initView() {
        datalist.add(MyWalletBean(R.drawable.mywallet_item_icon1, "收支明细"))
        datalist.add(MyWalletBean(R.drawable.mywallet_item_icon2, "立即提现"))
        mywallet_back.setOnClickListener { finish() }
        var linearlayotumanager = LinearLayoutManager(this)
        linearlayotumanager.orientation = RecyclerView.VERTICAL
        mywallet_rv.layoutManager = linearlayotumanager
        var adapter = object : BaseQuickAdapter<MyWalletBean, LBaseViewHolder>(R.layout.adapter_mywallet_item) {
            override fun convert(helper: LBaseViewHolder?, item: MyWalletBean?) {
                helper!!.setText(R.id.adapter_mywallet_item_name, item!!.name)
                helper.setGlide(R.id.adapter_mywallet_item_icon, item.icon)
            }
        }
        mywallet_rv.adapter = adapter
        adapter.setNewData(datalist)
    }

    override fun initPersenter() {
        mPersenter = MyWalletPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}