package com.example.solweather

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.solweather.adapters.DataFragmentAdapter
import com.example.solweather.ui.gallery.GalleryFragment
import com.example.solweather.ui.home.HomeFragment
import com.example.solweather.ui.map.MapFragment


class MainActivity : AppCompatActivity() {
    var navView: BottomNavigationView? = null
    private var viewPager: ViewPager2? =  null
    var galleryFragment: GalleryFragment? = null
    var homeFragment: HomeFragment? = null
    var mapFragment: MapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView  = findViewById(R.id.nav_view)
        viewPager = findViewById(R.id.pager)

        navView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewPager?.setCurrentItem(0, false)
                R.id.navigation_gallery -> viewPager?.setCurrentItem(1, false)
                R.id.navigation_map -> viewPager?.setCurrentItem(2, false)
            }
            true
        }
        viewPager?.offscreenPageLimit = 2
        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when (position) {
                    0 -> navView?.menu?.findItem(R.id.navigation_home)?.isChecked = true
                    1 -> navView?.menu?.findItem(R.id.navigation_gallery)?.isChecked = true
                    2 -> navView?.menu?.findItem(R.id.navigation_map)?.isChecked = true
                }
            }
        })
        setupViewPager(viewPager);
    }

    private fun setupViewPager(viewPager: ViewPager2?) {
        val adapter = DataFragmentAdapter(supportFragmentManager, lifecycle)
        galleryFragment = GalleryFragment()
        homeFragment = HomeFragment()
        mapFragment = MapFragment()
        adapter.addFragment(homeFragment!!)
        adapter.addFragment(galleryFragment!!)
        adapter.addFragment(mapFragment!!)
        viewPager!!.adapter = adapter
    }

}