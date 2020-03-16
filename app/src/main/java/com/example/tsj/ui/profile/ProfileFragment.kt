package com.example.tsj.ui.profile


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import com.example.tsj.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    private lateinit var context1: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.show()
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        root.bottom_exit.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)
            builder.setTitle ("Вы уверены что хотите вытти?")

            builder.setPositiveButton (android.R.string.yes){ d: DialogInterface, i: Int ->
                AppPreferences.clear = profile_text_email.text.toString()
                AppPreferences.clear = profile_text_password.text.toString()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)

                Toast.makeText (context, android.R.string.yes, Toast.LENGTH_SHORT) .show ()
            }

            builder.setNegativeButton (android.R.string.no) { d: DialogInterface, i: Int ->
                Toast.makeText (context,
                    android.R.string.no, Toast.LENGTH_SHORT) .show ()
            }

            builder.show ()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        change_password.setOnClickListener {
            findNavController().navigate(R.id.navigation_change_password)
        }
        initView()
    }

    private fun initView() {
            profile_text_email.setText(AppPreferences.email.toString())
            profile_text_password.setText("password")
    }
}
