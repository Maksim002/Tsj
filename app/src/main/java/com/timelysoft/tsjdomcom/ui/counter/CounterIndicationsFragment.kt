package com.timelysoft.tsjdomcom.ui.counter

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
import com.timelysoft.tsjdomcom.adapters.indication.CounterIndicationAdapter
import com.timelysoft.tsjdomcom.adapters.indication.CounterIndicationModel
import kotlinx.android.synthetic.main.fragment_counter_indications.*

class CounterIndicationsFragment : Fragment() {

    private val myAdapter = CounterIndicationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter_indications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        initArgument()
        getCountersIndicationsHome()
        getCountersIndicationsApartment()
        getCountersIndicationsService()
        getCountersIndications()
    }

    private fun initArgument() {
        counter_indication_add.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_testimony)
        }

        counters_confirmation_indications.setOnClickListener {
            findNavController().navigate(R.id.navigation_changing_reading)
        }

        counter_indication_changes.setOnClickListener {
            findNavController().navigate(R.id.navigation_explanation_history)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<CounterIndicationModel> = arrayListOf()
        list.add(CounterIndicationModel(""))
        list.add(CounterIndicationModel(""))
        list.add(CounterIndicationModel(""))
        list.add(CounterIndicationModel(""))
        list.add(CounterIndicationModel(""))

        myAdapter.update(list)
        counter_indication_recycler.adapter = myAdapter
    }

    private fun getCountersIndicationsHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCountersIndicationsHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counters_indications_home_out.setAdapter(adapterCountersIndicationsHome)

        counters_indications_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counters_indications_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counters_indications_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                counters_indications_home_out.clearFocus()
            }
        counters_indications_home_out.setOnClickListener {
            counters_indications_home_out.showDropDown()
        }
        counters_indications_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counters_indications_home_out.showDropDown()
                }
                if (!hasFocus && counters_indications_home_out.text!!.isNotEmpty()) {
                    counters_indications_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counters_indications_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counters_indications_home_out.clearFocus()
    }

    private fun getCountersIndicationsApartment() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCountersIndicationsApartment = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counters_indications_Apartment_out.setAdapter(adapterCountersIndicationsApartment)

        counters_indications_Apartment_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counters_indications_Apartment_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counters_indications_Apartment_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                counters_indications_Apartment_out.clearFocus()
            }
        counters_indications_Apartment_out.setOnClickListener {
            counters_indications_Apartment_out.showDropDown()
        }
        counters_indications_Apartment_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counters_indications_Apartment_out.showDropDown()
                }
                if (!hasFocus && counters_indications_Apartment_out.text!!.isNotEmpty()) {
                    counters_indications_Apartment.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counters_indications_Apartment.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counters_indications_Apartment_out.clearFocus()
    }

    private fun getCountersIndicationsService() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCountersIndicationsService = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counters_indications_service_out.setAdapter(adapterCountersIndicationsService)

        counters_indications_service_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counters_indications_service_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counters_indications_service_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                counters_indications_service_out.clearFocus()
            }
        counters_indications_service_out.setOnClickListener {
            counters_indications_service_out.showDropDown()
        }
        counters_indications_service_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counters_indications_service_out.showDropDown()
                }
                if (!hasFocus && counters_indications_service_out.text!!.isNotEmpty()) {
                    counters_indications_service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counters_indications_service.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counters_indications_service_out.clearFocus()
    }

    private fun getCountersIndications() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCountersIndications = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counters_indications_out.setAdapter(adapterCountersIndications)

        counters_indications_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counters_indications_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counters_indications_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                counters_indications_out.clearFocus()
            }
        counters_indications_out.setOnClickListener {
            counters_indications_out.showDropDown()
        }
        counters_indications_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counters_indications_out.showDropDown()
                }
                if (!hasFocus && counters_indications_out.text!!.isNotEmpty()) {
                    counters_indications.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counters_indications.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counters_indications_out.clearFocus()
    }
}