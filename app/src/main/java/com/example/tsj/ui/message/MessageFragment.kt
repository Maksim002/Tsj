package com.example.tsj.ui.message

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.message.ViewPagerAdapter
import com.example.tsj.service.AppPreferences
import com.example.tsj.ui.login.LoginActivity
import kotlinx.android.synthetic.main.alert_for_who.*
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment() {

    private lateinit var viewModel: MessagesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_message, container, false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        if (AppPreferences.isLogined) {
            msg_auth_view.visibility = View.VISIBLE
            msg_auth_image.visibility = View.GONE
            initTabLayout()
        } else {
            msg_auth_view.visibility = View.GONE
            msg_auth_image.visibility = View.VISIBLE
        }

    }

    private fun initViews() {

        msg_add.setOnClickListener {
            choose()
        }

        btn_auth.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java).putExtra("transition", true)
            startActivity(intent)
        }
    }

    private fun initTabLayout() {
        viewModel.messageTypes().observe(this, Observer {
            val pagerAdapter = ViewPagerAdapter(childFragmentManager)
            it.forEach { item ->
                pagerAdapter.addFragment(
                    MessagesFragment(
                        item.id
                    ), item.name)
            }
            msg_viewpager.adapter = pagerAdapter
            msg_tablayout.setupWithViewPager(msg_viewpager)
        })

    }

    private fun choose() {
        val view = LayoutInflater.from(context).inflate(R.layout.alert_for_who, null)
        val builder = AlertDialog.Builder(context)
            .setView(view)
        val dialog = builder.show()

        dialog.first_alert.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_message_chairman)
            dialog.dismiss()
        }

        dialog.second_alert.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_message_owner)
            dialog.dismiss()
        }
    }
}
