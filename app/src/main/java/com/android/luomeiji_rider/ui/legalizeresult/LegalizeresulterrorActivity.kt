package com.android.luomeiji_rider.ui.legalizeresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.tools.LActivityTool

class LegalizeresulterrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legalizeresultwith)
        LActivityTool.addActivity(this)
//        var body = FormBody.Builder().build()
//        MyOkHttp.getInstance()
//            .post("http://192.168.1.3:8088/t/t1", body, object : MyOkHttp.RequestCallBack {
//                override fun success(data: String?) {
//                    Log.d("main", data!!)
//                }
//
//                override fun fail(request: Request?, e: Exception?) {
//                    Log.d("main", e.toString())
//                }
//            })
    }
}