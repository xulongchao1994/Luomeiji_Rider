package com.android.luomeiji_rider.ui.main

import android.content.Intent
import android.os.Bundle


import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle

import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.tools.RUtils
import com.google.android.material.navigation.NavigationView

import androidx.drawerlayout.widget.DrawerLayout

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.luomeiji_rider.adapter.Viewpage_Adapter
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
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

    override fun initView() {
        setSupportActionBar(toolbar)
        mDrawertoggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawer_layout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )

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
            bundle.putString("ordertype", titlestringlist[i])
            fragment.arguments = bundle
            fragmentlist.add(fragment)
        }
        var viewpageradapter = Viewpage_Adapter(supportFragmentManager, fragmentlist, titlestringlist)
        main_viewpager.adapter = viewpageradapter
        main_tablayout.setupWithViewPager(main_viewpager)
    }
}
