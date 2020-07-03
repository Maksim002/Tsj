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
import kotlinx.android.synthetic.main.fragment_add_counter_all.*

class AddCounterAllFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_counter_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAddCounterAllHome()
        getAddCounterAllService()
        getAddCounterAllDate()

    }
    private fun getAddCounterAllHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterAllHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_counter_all_home_out.setAdapter(adapterAddCounterAllHome)

        add_counter_all_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_counter_all_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_counter_all_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_counter_all_home_out.clearFocus()
            }
        add_counter_all_home_out.setOnClickListener {
            add_counter_all_home_out.showDropDown()
        }
        add_counter_all_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_counter_all_home_out.showDropDown()
                }
                if (!hasFocus && add_counter_all_home_out.text!!.isNotEmpty()) {
                    add_counter_all_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_counter_all_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_counter_all_home_out.clearFocus()
    }

    private fun getAddCounterAllService() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterAllService = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_counter_all_service_out.setAdapter(adapterAddCounterAllService)

        add_counter_all_service_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_counter_all_service_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_counter_all_service_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_counter_all_service_out.clearFocus()
            }
        add_counter_all_service_out.setOnClickListener {
            add_counter_all_service_out.showDropDown()
        }
        add_counter_all_service_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_counter_all_service_out.showDropDown()
                }
                if (!hasFocus && add_counter_all_service_out.text!!.isNotEmpty()) {
                    add_counter_all_service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_counter_all_service.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_counter_all_service_out.clearFocus()
    }

    private fun getAddCounterAllDate() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddCounterAllDate = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_counter_all_date_out.setAdapter(adapterAddCounterAllDate)

        add_counter_all_date_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_counter_all_date_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_counter_all_date_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_counter_all_date_out.clearFocus()
            }
        add_counter_all_date_out.setOnClickListener {
            add_counter_all_date_out.showDropDown()
        }
        add_counter_all_date_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_counter_all_date_out.showDropDown()
                }
                if (!hasFocus && add_counter_all_date_out.text!!.isNotEmpty()) {
                    add_counter_all_date.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_counter_all_date.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_counter_all_date_out.clearFocus()
    }
}