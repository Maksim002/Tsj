package com.timelysoft.tsjdomcom.ui.setting

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArgument()
        getSettingUser()
    }

    private fun initArgument() {
        setting_general_information.setOnClickListener {
            findNavController().navigate(R.id.navigation_general_information)
        }

        setting_general_address.setOnClickListener {
            findNavController().navigate(R.id.navigation_address)
        }

        setting_requisites.setOnClickListener {
            findNavController().navigate(R.id.navigation_bank_details)
        }

        setting_staff.setOnClickListener {
            findNavController().navigate(R.id.navigation_staff)
        }

    }
    private fun getSettingUser() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterSettingUser = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        setting_user_out.setAdapter(adapterSettingUser)

        setting_user_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        setting_user_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                setting_user_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                setting_user_out.clearFocus()
            }
        setting_user_out.setOnClickListener {
            setting_user_out.showDropDown()
        }
        setting_user_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    setting_user_out.showDropDown()
                }
                if (!hasFocus && setting_user_out.text!!.isNotEmpty()) {
                    setting_user.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    setting_user.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        setting_user_out.clearFocus()
    }
}