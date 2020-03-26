package com.example.tsj.ui.profile


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tsj.R
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_profile.*


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
        initHint()
    }

    fun initHint() {
        change_password_old.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (!hasFocus && change_password_old.text!!.isNotEmpty()) {
                change_password_old_input.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
        change_password_new.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (!hasFocus && change_password_new.text!!.isNotEmpty()) {
                change_password_new_input.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
        change_password_accept.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (!hasFocus && change_password_accept.text!!.isNotEmpty()) {
                change_password_accept_input.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
    }
}
