package com.android.luomeiji_rider.ui.mywallet

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.base.LBaseViewHolder
import com.android.luomeiji_rider.bean.MyWalletBean
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.tools.LActivityTool
import com.android.luomeiji_rider.ui.bindbank.DontBindBankActivity
import com.android.luomeiji_rider.ui.getmoeny.GetMoenyActivity
import com.android.luomeiji_rider.ui.withdraw.WithdrawActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_mywallet.*

class MyWalletActivity : LBaseAppCompatActivity<MyWalletPersenter>(), IMyWalletView {
    override fun initlayout(): Int {
        return R.layout.activity_mywallet
    }

    var datalist = arrayListOf<MyWalletBean>()
    override fun initView() {
        LActivityTool.addActivity(this)
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
        adapter.setOnItemClickListener { adapter, view, position ->
            when (datalist[position].name) {
                "收支明细" -> {
                    startActivity(Intent(this, GetMoenyActivity::class.java))
                }
                "立即提现" -> {
                    if (!UserData_Java.isbingbank) {
                        startActivity(Intent(this, DontBindBankActivity::class.java))
                    } else {
                        startActivity(Intent(this, WithdrawActivity::class.java))
                    }
                }
            }
        }
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