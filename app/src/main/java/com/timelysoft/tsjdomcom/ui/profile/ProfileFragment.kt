package com.timelysoft.tsjdomcom.ui.profile

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        root.profile_exit_btn.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle("Вы уверены что хотите выйти?")

            builder.setPositiveButton(android.R.string.yes) { d: DialogInterface, i: Int ->
                AppPreferences.clear()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(context, android.R.string.yes, Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { d: DialogInterface, i: Int ->
                Toast.makeText(
                    context,
                    android.R.string.no, Toast.LENGTH_SHORT
                ).show()
            }

            builder.show()
        }
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initHint()
    }

    private fun initView() {


        profile_email.setText(AppPreferences.email.toString())
        profile_email.tag = profile_email.keyListener
        profile_email.keyListener = null

        profile_password.setText("password")

        profile_password.tag = profile_password.keyListener
        profile_password.keyListener = null

        profile_change_password.setOnClickListener {
            findNavController().navigate(R.id.navigation_change_password)
        }
    }

    private fun initHint() {
        if (profile_email.text.isNotEmpty()) {
            profile_email_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        if (profile_password.text.isNotEmpty()){
            profile_password_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }
}
