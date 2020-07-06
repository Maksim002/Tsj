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
import com.timelysoft.tsjdomcom.adapters.counter.CounterAdapter
import com.timelysoft.tsjdomcom.adapters.counter.CounterModel
import kotlinx.android.synthetic.main.fragment_counter.*

class CounterFragment : Fragment() {

    private val myAdapter = CounterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getCounterHome()
        getApartmentCounter()
        getCounterService()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        counters_add_apartments.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_counter_all)
        }

        counters_add_apartment.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_counter_current)
        }

        counters_indications.setOnClickListener {
            findNavController().navigate(R.id.navigation_counters_indications)
        }

        counters_total_debt.setOnClickListener {
            findNavController().navigate(R.id.navigation_debts)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<CounterModel> = arrayListOf()
        list.add(CounterModel(""))
        list.add(CounterModel(""))
        list.add(CounterModel(""))
        list.add(CounterModel(""))
        list.add(CounterModel(""))

        myAdapter.update(list)
        counter_recycler.adapter = myAdapter
    }

    private fun getCounterHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCounterHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counter_home_out.setAdapter(adapterCounterHome)

        counter_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counter_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counter_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                counter_home_out.clearFocus()
            }
        counter_home_out.setOnClickListener {
            counter_home_out.showDropDown()
        }
        counter_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counter_home_out.showDropDown()
                }
                if (!hasFocus && counter_home_out.text!!.isNotEmpty()) {
                    counter_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counter_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counter_home_out.clearFocus()
    }

    private fun getApartmentCounter() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCounterHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counter_apartment_counter.setAdapter(adapterCounterHome)

        counter_apartment_counter.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counter_apartment_counter.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counter_apartment_counter.showDropDown()
                parent.getItemAtPosition(position).toString()
                counter_apartment_counter.clearFocus()
            }
        counter_apartment_counter.setOnClickListener {
            counter_apartment_counter.showDropDown()
        }
        counter_apartment_counter.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counter_apartment_counter.showDropDown()
                }
                if (!hasFocus && counter_apartment_counter.text!!.isNotEmpty()) {
                    counter_apartment.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counter_apartment.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counter_apartment_counter.clearFocus()
    }

    private fun getCounterService() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterCounterService = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        counter_service_out.setAdapter(adapterCounterService)

        counter_service_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        counter_service_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                counter_service_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                counter_service_out.clearFocus()
            }
        counter_service_out.setOnClickListener {
            counter_service_out.showDropDown()
        }
        counter_service_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    counter_service_out.showDropDown()
                }
                if (!hasFocus && counter_service_out.text!!.isNotEmpty()) {
                    counter_service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    counter_service.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        counter_service_out.clearFocus()
    }
}