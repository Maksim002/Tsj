package com.timelysoft.tsjdomcom.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.message.ViewPagerAdapter
import com.timelysoft.tsjdomcom.ui.message.send.MassageListFragment
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_messages_chairman.*

class MessagesChairmanFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_messages_chairman, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
        iniClick()
    }

    private fun iniClick() {
        message_list_add.setOnClickListener {
            findNavController().navigate(R.id.navigation_message_chairman)
        }
    }

    private fun initTabLayout() {
        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(MassageListFragment(), "Входящие")
        pagerAdapter.addFragment(MassageListFragment(), "Исходящие")
        message_list_viewpager.adapter = pagerAdapter
        message_list_tab_layout.setupWithViewPager(message_list_viewpager)
    }
}