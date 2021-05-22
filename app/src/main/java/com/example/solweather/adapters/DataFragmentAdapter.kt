package com.example.solweather.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.solweather.ui.dashboard.DashboardFragment
import com.example.solweather.ui.home.HomeFragment
import com.example.solweather.ui.notifications.NotificationsFragment

class DataFragmentAdapter(fragmentManager: FragmentManager, b: Lifecycle?) :
        FragmentStateAdapter(fragmentManager, b!!) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }
}