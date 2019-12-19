package com.android.luomeiji_rider.ui.bindbank

import android.content.Context
import android.content.SharedPreferences
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.tools.LActivityTool
import com.vondear.rxtool.view.RxToast
import kotlinx.android.synthetic.main.activity_bindbank.*


class BindBankActivity : LBaseAppCompatActivity<BindBankPersenter>(), IBindBankView {
    override fun initlayout(): Int {
        return R.layout.activity_bindbank
    }

    var name_text = ""
    var idnumber = ""
    var bankname = ""
    var banknumber = ""
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun initView() {
        LActivityTool.addActivity(this)
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        editor = sp!!.edit()
        bindbank_back.setOnClickListener { finish() }
        bindbank_tijiao.setOnClickListener { postuserbankinfo() }
    }

    private fun postuserbankinfo() {
        name_text = bindbank_name.text.toString()
        idnumber = bindbank_idnumber.text.toString()
        bankname = bindbank_bankname.text.toString()
        banknumber = bindbank_banknumber.text.toString()
        if (name_text == "") {
            RxToast.normal(resources.getString(R.string.bindbank_name))
            return
        }
        if (idnumber == "") {
            RxToast.normal(resources.getString(R.string.bindbank_idnumber))
            return
        }
        if (bankname == "") {
            RxToast.normal(resources.getString(R.string.bindbank_bankname))
            return
        }
        if (banknumber == "") {
            RxToast.normal(resources.getString(R.string.bindbank_banknumber))
            return
        }
        editor!!.putString("bankusername", name_text)
        editor!!.putString("bankuseridnumber", idnumber)
        editor!!.putString("banknumber", banknumber)
        editor!!.putString("bankname", bankname)
        editor!!.putBoolean("isbindbank", true)
        editor!!.commit()
        UserData_Java.isbingbank = true
        RxToast.showToast("提交成功")
//        if (LActivityTool.isExistActivity(this, applicationContext.packageName, DontBindBankActivity::class.java.canonicalName)) {
//            RxLogTool.i(applicationContext.packageName)
//            RxLogTool.i(DontBindBankActivity::class.java.canonicalName)
//            LActivityTool.finishActivity(DontBindBankActivity::class.java)
//            LActivityTool.finishActivity(this)
//        }
        finish()
    }

    override fun initPersenter() {
        mPersenter = BindBankPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}