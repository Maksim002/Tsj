package com.example.tsj.ui.history


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.tsj.R
import com.google.android.material.tabs.TabLayout
import com.example.tsj.adapters.pager.HistoryPagerAdapter


/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    //    Maksim

    lateinit var vP: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_history, container, false)

        tabLayout = root.findViewById(R.id.tabLs)
        vP = root.findViewById(R.id.viewPi)

        tabLayout.addTab(tabLayout.newTab().setText("Новый ЛС"))
        tabLayout.addTab(tabLayout.newTab().setText("Сохраненные"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.tabMode  = TabLayout.MODE_FIXED

        val adapter = HistoryPagerAdapter(this, childFragmentManager, tabLayout.tabCount)
        vP.adapter = adapter

        vP.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vP.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        (activity as AppCompatActivity).supportActionBar?.show()
        return root
    }
    //
}
