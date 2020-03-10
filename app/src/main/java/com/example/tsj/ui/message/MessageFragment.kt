package com.example.tsj.ui.message

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.message.MessageViewPagerAdapter
import com.example.tsj.ui.message.fragments.InboxFragment
import com.example.tsj.ui.message.fragments.OutboxFragment
import kotlinx.android.synthetic.main.alert_for_who.*
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {


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
            choose()
        }
    }

    private fun initTabLayout() {
        val pagerAdapter = MessageViewPagerAdapter(activity!!.supportFragmentManager)
        pagerAdapter.addFragment(InboxFragment(), "Входящие")
        pagerAdapter.addFragment(OutboxFragment(), "Исходящие")
        msg_viewpager.adapter = pagerAdapter
        msg_tablayout.setupWithViewPager(msg_viewpager)
    }


    private fun choose() {
        val view = LayoutInflater.from(context).inflate(R.layout.alert_for_who, null)
        val builder = AlertDialog.Builder(context)
            .setView(view)
        val dialog = builder.show()

        dialog.first_alert.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_message)
            dialog.dismiss()
        }

        dialog.second_alert.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_message)
            dialog.dismiss()
        }
    }
}
