package com.timelysoft.tsjdomcom.ui.payments

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
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArguments()
        getPaymentHome()
        getPaymentCounter()
        getPaymentService()
    }

    private fun initArguments() {
        payments_show_payments.setOnClickListener {
            findNavController().navigate(R.id.navigation_show_payment)
        }
    }

    private fun getPaymentHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterPaymentHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        payment_home_out.setAdapter(adapterPaymentHome)

        payment_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        payment_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                payment_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                payment_home_out.clearFocus()
            }
        payment_home_out.setOnClickListener {
            payment_home_out.showDropDown()
        }
        payment_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    payment_home_out.showDropDown()
                }
                if (!hasFocus && payment_home_out.text!!.isNotEmpty()) {
                    payment_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    payment_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        payment_home_out.clearFocus()
    }

    private fun getPaymentCounter() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterPaymentCounter = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        payment_apartment_counter.setAdapter(adapterPaymentCounter)

        payment_apartment_counter.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        payment_apartment_counter.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                payment_apartment_counter.showDropDown()
                parent.getItemAtPosition(position).toString()
                payment_apartment_counter.clearFocus()
            }
        payment_apartment_counter.setOnClickListener {
            payment_apartment_counter.showDropDown()
        }
        payment_apartment_counter.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    payment_apartment_counter.showDropDown()
                }
                if (!hasFocus && payment_apartment_counter.text!!.isNotEmpty()) {
                    payment_apartment.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    payment_apartment.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        payment_apartment_counter.clearFocus()
    }

    private fun getPaymentService() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterPaymentService = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        payment_service_out.setAdapter(adapterPaymentService)

        payment_service_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        payment_service_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                payment_service_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                payment_service_out.clearFocus()
            }
        payment_service_out.setOnClickListener {
            payment_service_out.showDropDown()
        }
        payment_service_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    payment_service_out.showDropDown()
                }
                if (!hasFocus && payment_service_out.text!!.isNotEmpty()) {
                    payment_service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    payment_service.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        payment_service_out.clearFocus()
    }

    override fun onStart() {
        super.onStart()
        date_payment.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }
}