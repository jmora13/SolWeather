package com.example.solweather

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.solweather.adapters.DataFragmentAdapter
import com.example.solweather.ui.dashboard.DashboardFragment
import com.example.solweather.ui.home.HomeFragment
import com.example.solweather.ui.notifications.NotificationsFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.Preferences

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var navView: BottomNavigationView? = null
    val Context.dataStore by preferencesDataStore(name = "settings")
    private var viewPager: ViewPager2? =  null
    var dashboardFragment: DashboardFragment? = null
    var homeFragment: HomeFragment? = null
    var notificationsFragment: NotificationsFragment? = null
//    private val mOnNavigationItemselectedListener =
//            BottomNavigationView.OnNavigationItemSelectedListener { item ->
//                when (item.itemId) {
//                    R.id.navigation_home -> {
//                        pager.currentItem = 0
//                        return@OnNavigationItemSelectedListener false
//                    }
//                    R.id.navigation_dashboard -> {
//                        pager.currentItem = 1
//                        return@OnNavigationItemSelectedListener false
//                    }
//                    R.id.navigation_notifications -> {
//                        pager.currentItem = 2
//                        return@OnNavigationItemSelectedListener false
//                    }
//                }
//                false
//            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView  = findViewById(R.id.nav_view)
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemselectedListener)
        //val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        viewPager = findViewById(R.id.pager)

        navView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewPager?.setCurrentItem(0, false)
                R.id.navigation_dashboard -> viewPager?.setCurrentItem(1, false)
                R.id.navigation_notifications -> viewPager?.setCurrentItem(2, false)
            }
            false
        }
        viewPager?.offscreenPageLimit = 2
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
               // viewPager.setCurrentItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> navView?.menu?.findItem(R.id.navigation_home)?.isChecked =
                            true
                    1 -> navView?.menu?.findItem(R.id.navigation_dashboard)?.isChecked = true

                    2 -> navView?.menu?.findItem(R.id.navigation_notifications)?.isChecked = true
                }
            }
        })
//        viewPager.adapter = dataFragmentAdapter
//        viewPager.offscreenPageLimit = 2
        setupViewPager(viewPager);
    }

    private fun setupViewPager(viewPager: ViewPager2?) {
        val adapter = DataFragmentAdapter(supportFragmentManager, lifecycle)
        dashboardFragment = DashboardFragment()
        homeFragment = HomeFragment()
        notificationsFragment = NotificationsFragment()
        adapter.addFragment(homeFragment!!)
        adapter.addFragment(dashboardFragment!!)
        adapter.addFragment(notificationsFragment!!)
        viewPager!!.adapter = adapter
    }

}