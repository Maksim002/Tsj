package com.timelysoft.tsjdomcom.ui.message

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.message.ViewPagerAdapter
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.ui.login.LoginActivity
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
            message_auth_view.visibility = View.VISIBLE
            message_auth_image.visibility = View.GONE
            MainActivity.alert.show()
            initTabLayout()
        } else {
            message_auth_view.visibility = View.GONE
            message_auth_image.visibility = View.VISIBLE
        }

    }

    private fun initViews() {

        message_add.setOnClickListener {
            choose()
        }

        message_btn_auth.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java).putExtra("transition", true)
            startActivity(intent)
        }
    }

    private fun initTabLayout() {
        viewModel.messageTypes().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    val pagerAdapter = ViewPagerAdapter(childFragmentManager)
                    data!!.forEach { item ->
                        pagerAdapter.addFragment(
                            MessagesFragment(item.id), item.name
                        )
                    }
                    message_viewpager.adapter = pagerAdapter
                    message_tab_layout.setupWithViewPager(message_viewpager)
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
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
