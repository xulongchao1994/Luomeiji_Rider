package com.android.luomeiji_rider.ui.legalizeresult

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.ui.main.MainActivity
import com.vondear.rxtool.RxActivityTool
import kotlinx.android.synthetic.main.activity_legalizeresultsuccess.*

class LegalizeresultsuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legalizeresultsuccess)
        RxActivityTool.addActivity(this)
        legalizeresultsuccess_bt.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}