package com.timelysoft.tsjdomcom.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_history.at_home_address
import kotlinx.android.synthetic.main.fragment_history.at_home_address_text
import kotlinx.android.synthetic.main.fragment_navigation_at_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_at_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        getAutoAddress()
        initClick()

    }

    private fun initClick() {
        home_user.setOnClickListener {
            findNavController().navigate(R.id.navigation_user)
        }

        home_providers.setOnClickListener {
            findNavController().navigate(R.id.navigation_provider)
        }

        home_user_request.setOnClickListener {
            findNavController().navigate(R.id.navigation_user_request)
        }

        home_service.setOnClickListener {
            findNavController().navigate(R.id.navigation_service)
        }

        home_counters.setOnClickListener {
            findNavController().navigate(R.id.navigation_counters)
        }

        home_payments_invoices.setOnClickListener {
            findNavController().navigate(R.id.navigation_payments_invoices)
        }

        home_estimates.setOnClickListener {
            findNavController().navigate(R.id.navigation_estimate)
        }

        home_expenses_receipts.setOnClickListener {
            findNavController().navigate(R.id.navigation_expense_receipts)
        }

        home_references_residence.setOnClickListener {
            findNavController().navigate(R.id.navigation_reference_residence)
        }

        home_notifying_residents.setOnClickListener {
            findNavController().navigate(R.id.navigation_notifying_residents)
        }

        home_voice.setOnClickListener {
            findNavController().navigate(R.id.navigation_voice)
        }
    }

    override fun onStart() {
        super.onStart()
        initColor()
    }

    private fun initColor() {
        at_home_address_text.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.whiteColor))
    }

    private fun getAutoAddress() {
        val data = arrayListOf("7 небо, Токомбаева, д.53/2 кв 11", "7 небо, Токомбаева, д.53/2 кв 11",
            "7 небо, Токомбаева, д.53/2 кв 48",
            "7 небо, Токомбаева, д.53/2 кв 555", "7 небо, Токомбаева, д.53/2 кв 100000")

        val adapterAddress = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, data)
        at_home_address.setAdapter(adapterAddress)

        at_home_address.keyListener = null

        at_home_address.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
                at_home_address.showDropDown()
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
                    at_home_address_text.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.whiteColor))
                    at_home_address_text.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        at_home_address.clearFocus()
    }
}
