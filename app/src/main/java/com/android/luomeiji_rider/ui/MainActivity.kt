package com.android.luomeiji_rider.ui

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
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    private var mDrawertoggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)

        setSupportActionBar(toolbar)
        mDrawertoggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getHeaderView(0).findViewById<View>(R.id.imageView).setOnClickListener { view ->
            //                switch (view.getId()) {
            //                    case R.id.imageView:
            //                    case R.id.main_text:
            Toast.makeText(this@MainActivity, view.id.toString(), Toast.LENGTH_SHORT).show()
            //                        break;
            //                    case R.id.textView:
            //                        Toast.makeText(MainActivity.this, view.getId(), Toast.LENGTH_SHORT).show();
            //                        break;
            //                }
            drawer.closeDrawer(navigationView)
        }
        navigationView.setNavigationItemSelectedListener { menuItem ->
            Toast.makeText(this@MainActivity, menuItem.itemId.toString(), Toast.LENGTH_SHORT).show()
            drawer.closeDrawer(navigationView)
            false
        }

        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val home_me = resources.getDrawable(R.drawable.home_me)
        val home_me_2 = RUtils.zoomDrawable(home_me, 220, 220)
        toolbar.navigationIcon = home_me_2
        intview()
    }

    private fun intview() {
        main_tablayout.addTab(main_tablayout.newTab().setText("新订单"))
        main_tablayout.addTab(main_tablayout.newTab().setText("待取货"))
        main_tablayout.addTab(main_tablayout.newTab().setText("待送达"))
    }


    //    @Override
    //    protected void onPostCreate(Bundle savedInstanceState) {
    //        super.onPostCreate(savedInstanceState);
    //        mDrawertoggle.syncState();
    //    }
    //
    //    @Override
    //    public void onConfigurationChanged(Configuration newConfig) {
    //        super.onConfigurationChanged(newConfig);
    //        mDrawertoggle.onConfigurationChanged(newConfig);
    //    }
}
