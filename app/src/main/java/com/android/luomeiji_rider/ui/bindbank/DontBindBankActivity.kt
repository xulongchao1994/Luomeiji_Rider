package com.android.luomeiji_rider.ui.bindbank

import android.content.Intent
import android.text.Layout
import androidx.core.content.ContextCompat
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.tools.LActivityTool
import com.vondear.rxtool.RxTextTool
import kotlinx.android.synthetic.main.activity_dontbindbank.*

class DontBindBankActivity : LBaseAppCompatActivity<BindBankPersenter>(), IBindBankView {
    override fun initlayout(): Int {
        return R.layout.activity_dontbindbank
    }

    override fun initView() {
        LActivityTool.addActivity(this)
        RxTextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("为了您的账户资金安全，罗美集司机端").append("只支持绑定一张银行卡")
                .setForegroundColor(ContextCompat.getColor(this, R.color.dontbindbank_text))
                .append(",确保资金只能提现到您的银行卡中，他人无法盗用")
                .into(dotbindbank_text)
        dontbingbank_lijibangka.setOnClickListener {
            startActivity(Intent(this, BindBankActivity::class.java))
            finish()
        }
        dontbingbank_back.setOnClickListener {
            finish()
        }
    }

    override fun initPersenter() {
        mPersenter = BindBankPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}