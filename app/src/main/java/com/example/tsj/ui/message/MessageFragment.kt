package com.example.tsj.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.tsj.R
import com.example.tsj.adapters.message.FragmentAdapter
import com.example.tsj.ui.message.fragments.ForWhoFragment
import com.example.tsj.ui.message.fragments.InboxFragment
import com.example.tsj.ui.message.fragments.OutboxFragment
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_message.view.*

class MessageFragment : Fragment() {


    val forWhoFragment = ForWhoFragment()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_message, container, false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
        initViews()
    }

    private fun initViews() {

        msg_add.setOnClickListener {
            forWhoFragment.show(fragmentManager!!, "ForWhoFragment")
        }
    }

    private fun initTabLayout() {
        val pagerAdapter = FragmentAdapter(activity!!.supportFragmentManager)
        pagerAdapter.addFragment(InboxFragment(), "Входящие")
        pagerAdapter.addFragment(OutboxFragment(), "Исходящие")
        msg_viewpager.adapter = pagerAdapter
        msg_tablayout.setupWithViewPager(msg_viewpager)
    }
}
