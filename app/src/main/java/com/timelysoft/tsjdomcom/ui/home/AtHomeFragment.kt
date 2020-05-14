package com.timelysoft.tsjdomcom.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_history.*

class AtHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_at_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAutoAddress()

//        wefefe.setOnClickListener {
//            findNavController().navigate(R.id.navigation_owners)
//        }
    }

    private fun getAutoAddress() {
        val data = arrayListOf("7 небо, Токомбаева, д.53/2 кв 11", "7 небо, Токомбаева, д.53/2 кв 11",
            "7 небо, Токомбаева, д.53/2 кв 48",
            "7 небо, Токомбаева, д.53/2 кв 555", "7 небо, Токомбаева, д.53/2 кв 100000")

        val adapterAddress = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, data)
        at_home_address.setAdapter(adapterAddress)

        at_home_address.keyListener = null

        at_home_address.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                at_home_address.showDropDown()
                at_home_address_text.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
            }
        at_home_address.setOnClickListener {
            at_home_address.showDropDown()
        }
        at_home_address.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    at_home_address.showDropDown()
                }
                if (!hasFocus && at_home_address.text!!.isNotEmpty()) {
                    history_service_out.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
                    at_home_address_text.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        at_home_address.clearFocus()
    }
}
