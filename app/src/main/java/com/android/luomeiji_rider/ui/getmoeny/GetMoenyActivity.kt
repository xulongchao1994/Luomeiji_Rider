package com.android.luomeiji_rider.ui.getmoeny

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.tools.LActivityTool
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_getmoeny.*

class GetMoenyActivity : LBaseAppCompatActivity<GetMoenyPersenter>(), IGetMoenyView {
    override fun initlayout(): Int {
        return R.layout.activity_getmoeny
    }

    var orderlistdata = arrayListOf<String>()
    override fun initView() {
        LActivityTool.addActivity(this)
        getmoeny_back.setOnClickListener { finish() }
        var linearlayotumanager = LinearLayoutManager(this)
        linearlayotumanager.orientation = RecyclerView.VERTICAL
        getmoeny_rv.layoutManager = linearlayotumanager
        var adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_getmoeny_item) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.adapter_getmoeny_moeny, item!!)
            }
        }
        getmoeny_rv.adapter = adapter
        for (i in 0..15) {
            orderlistdata.add(i.toString())
        }
        adapter.setNewData(orderlistdata)
    }

    override fun initPersenter() {
        mPersenter = GetMoenyPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}