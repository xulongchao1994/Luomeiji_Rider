package com.android.luomeiji_rider.ui.usercomment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_orderlist.*
import kotlinx.android.synthetic.main.activity_usercomment.*

class UserCommentActivity : LBaseAppCompatActivity<UserCommentPersenter>(), IUserCommentView {
    override fun initlayout(): Int {
        return R.layout.activity_usercomment
    }

    var orderlistdata = arrayListOf<String>()
    override fun initView() {
        usercomment_back.setOnClickListener { finish() }
        var linearlayotumanager = LinearLayoutManager(this)
        linearlayotumanager.orientation = RecyclerView.VERTICAL
        usercomment_rv.layoutManager = linearlayotumanager
        var adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_usercomment_item) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.adapter_usercomment_item_number, item!!)
            }
        }
        usercomment_rv.adapter = adapter
        for (i in 0..15) {
            orderlistdata.add(i.toString())
        }
        adapter.setNewData(orderlistdata)
    }

    override fun initPersenter() {
        mPersenter = UserCommentPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }


    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}