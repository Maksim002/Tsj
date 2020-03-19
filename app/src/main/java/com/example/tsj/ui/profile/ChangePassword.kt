package com.example.tsj.ui.profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.profile_text_email
import kotlinx.android.synthetic.main.fragment_change_password.profile_text_password
import kotlinx.android.synthetic.main.fragment_profile.*
import java.lang.Exception


class ChangePassword : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile_text_email.setText(AppPreferences.email)
        profile_text_email.setTag(profile_text_email.getKeyListener())
        profile_text_email.setKeyListener(null)
    }

}
