package com.example.tsj.adapters.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tsj.ui.history.HistoryFragment
import com.example.tsj.ui.history.fragments.HistoryListFragment
import com.example.tsj.ui.history.fragments.SaveFragment

class HistoryPagerAdapter(private val myContext: HistoryFragment, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    //    Maksim

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return HistoryListFragment()
            }
            1 -> {
                return SaveFragment()
            }
            else -> return HistoryListFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}