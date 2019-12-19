package com.android.luomeiji_rider.ui.main


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.adapter.Viewpage_Adapter
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.tools.LActivityTool
import com.android.luomeiji_rider.tools.RUtils
import com.android.luomeiji_rider.ui.login.LoginActivity
import com.android.luomeiji_rider.ui.main_framgent.Main_fragment
import com.android.luomeiji_rider.ui.mywallet.MyWalletActivity
import com.android.luomeiji_rider.ui.orderlist.OrderListActivity
import com.android.luomeiji_rider.ui.usercomment.UserCommentActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : LBaseAppCompatActivity<MainPersenter>(), IMainView {
    override fun initlayout(): Int {
        return R.layout.activity_main
    }

    var sp: SharedPreferences? = null
    override fun initView() {
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        setSupportActionBar(toolbar)
        mDrawertoggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawer_layout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )

        LActivityTool.addActivity(this)
        nav_view.getHeaderView(0).findViewById<View>(R.id.imageView).setOnClickListener { view ->
            Toast.makeText(this@MainActivity, view.id.toString(), Toast.LENGTH_SHORT).show()
            drawer_layout.closeDrawer(nav_view)
        }
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_orderlist -> {
                    startActivity(Intent(this, OrderListActivity::class.java))
                }
                R.id.nav_cehngke -> {
                    startActivity(Intent(this, UserCommentActivity::class.java))
                }
                R.id.nav_moeny -> {
                    startActivity(Intent(this, MyWalletActivity::class.java))
                }
                R.id.nav_outlogin -> {
                    outlogin()
                }
            }
            drawer_layout.closeDrawer(nav_view)
            false
        }

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val home_me = resources.getDrawable(R.drawable.home_me)
        val home_me_2 = RUtils.zoomDrawable(home_me, 220, 220)
        toolbar.navigationIcon = home_me_2
        intview()
    }

    private fun outlogin() {
        AlertDialog.Builder(this)
                .setTitle("是否退出登录")
                .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                    }
                })
                .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val editor = sp!!.edit()
                        editor.putBoolean("islogin", false)
                        if (!sp!!.getBoolean("rememberpsw", false)) {
                            editor.putString("username", "")
                            editor.putString("password", "")
                        }
                        editor.putString("token", "")
                        editor.putString("uid", "")
                        editor.commit()
                        UserData_Java.islogin = false
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                }).show()
    }

    override fun initPersenter() {
        mPersenter = MainPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }

    private var mDrawertoggle: ActionBarDrawerToggle? = null
    var fragmentlist = arrayListOf<Fragment>()
    var titlestringlist = arrayListOf<String>()
    private fun intview() {
        titlestringlist.add("新订单")
        titlestringlist.add("待取货")
        titlestringlist.add("待送达")
        main_tablayout.addTab(main_tablayout.newTab().setText("新订单"))
        main_tablayout.addTab(main_tablayout.newTab().setText("待取货"))
        main_tablayout.addTab(main_tablayout.newTab().setText("待送达"))
        for (i in 0 until titlestringlist.size) {
            var fragment = Main_fragment()
            var bundle = Bundle()
            bundle.putString("ordertype", (i + 1).toString())
            fragment.arguments = bundle
            fragmentlist.add(fragment)
        }
        var viewpageradapter = Viewpage_Adapter(supportFragmentManager, fragmentlist, titlestringlist)
        main_viewpager.adapter = viewpageradapter
        main_tablayout.setupWithViewPager(main_viewpager)
    }
}
