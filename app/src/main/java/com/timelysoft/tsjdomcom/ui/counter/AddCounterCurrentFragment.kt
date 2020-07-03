package com.timelysoft.tsjdomcom.ui.counter

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_add_counter_current.*

class AddCounterCurrentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_counter_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAddCounterCurrentHome()
        getAddCounterCurrentApartment()
        getAddCounterCurrentService()
        getAddCounterCurrentDate()
    }

    private fun getAddCounterCurrentHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterCurrentHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_counter_current_home_out.setAdapter(adapterAddCounterCurrentHome)

        add_counter_current_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_counter_current_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_counter_current_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_counter_current_home_out.clearFocus()
            }
        add_counter_current_home_out.setOnClickListener {
            add_counter_current_home_out.showDropDown()
        }
        add_counter_current_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_counter_current_home_out.showDropDown()
                }
                if (!hasFocus && add_counter_current_home_out.text!!.isNotEmpty()) {
                    add_counter_current_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_counter_current_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_counter_current_home_out.clearFocus()
    }

    private fun getAddCounterCurrentApartment() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterCurrentApartment = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_apartment_current_out.setAdapter(adapterAddCounterCurrentApartment)

        add_apartment_current_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_apartment_current_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_apartment_current_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_apartment_current_out.clearFocus()
            }
        add_apartment_current_out.setOnClickListener {
            add_apartment_current_out.showDropDown()
        }
        add_apartment_current_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_apartment_current_out.showDropDown()
                }
                if (!hasFocus && add_apartment_current_out.text!!.isNotEmpty()) {
                    add_counter_apartment.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_counter_apartment.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_apartment_current_out.clearFocus()
    }

    private fun getAddCounterCurrentService() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterCurrentService = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_current_service_out.setAdapter(adapterAddCounterCurrentService)

        add_current_service_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_current_service_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_current_service_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_current_service_out.clearFocus()
            }
        add_current_service_out.setOnClickListener {
            add_current_service_out.showDropDown()
        }
        add_current_service_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_current_service_out.showDropDown()
                }
                if (!hasFocus && add_current_service_out.text!!.isNotEmpty()) {
                    add_current_service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_current_service.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_current_service_out.clearFocus()
    }

    private fun getAddCounterCurrentDate() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterCurrentDate = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_current_date_out.setAdapter(adapterAddCounterCurrentDate)

        add_current_date_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_current_date_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_current_date_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_current_date_out.clearFocus()
            }
        add_current_date_out.setOnClickListener {
            add_current_date_out.showDropDown()
        }
        add_current_date_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_current_date_out.showDropDown()
                }
                if (!hasFocus && add_current_date_out.text!!.isNotEmpty()) {
                    add_current_date.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_current_date.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_current_date_out.clearFocus()
    }
}