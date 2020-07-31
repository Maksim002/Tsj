package com.timelysoft.tsjdomcom.ui.provider

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.SupplierAccountsAdapter
import com.timelysoft.tsjdomcom.adapters.provider.SupplierAccountsListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoices
import com.timelysoft.tsjdomcom.service.model.provider.SupplierAccountsModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_supplier_accounts.*
import java.util.*
import kotlin.collections.ArrayList

class SupplierAccountsFragment : Fragment(), SupplierAccountsListener {
    private var viewModel = ProviderViewModel()
    private val myAdapter = SupplierAccountsAdapter(this)

    private var mLastClickTime: Long = 0
    private var providerId: Int = 0

    private lateinit var dataFrom: String
    private lateinit var dataTo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier_accounts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAutoDatesFrom()
        getAutoDatesTo()
        getSupplierAccounts()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        supplier_accounts_add_invoice.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_invoice)
        }

        supplier_accounts_search.setOnClickListener {
            viewModel.supplierAccounts(dataFrom, dataTo, providerId)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                    val msg = result.msg
                    val data = result.data
                    when (result.status) {
                        Status.SUCCESS -> {
                            myAdapter.update(data!!)
                            myAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }

    private fun initRecycler() {
        supplier_accounts_recycler.adapter = myAdapter
    }

    override fun supplierAccountsOnClick(item: SupplierAccountsModel) {
        viewModel.supplierAccountsDelete(providerId).observe(this, androidx.lifecycle.Observer { result ->
            val msg = result.msg
            when (result.status) {
                Status.SUCCESS -> {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR, Status.NETWORK -> {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        supplier_accounts_from_out.keyListener = null;
        supplier_accounts_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                supplier_accounts_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        supplier_accounts_from_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dataFrom = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    }, year, month, day
                )
                picker.show()
                supplier_accounts_from_out.clearFocus()
            }
        }
    }

    private fun getAutoDatesTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        supplier_accounts_to_out.keyListener = null;
        supplier_accounts_to_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                supplier_accounts_to.defaultHintTextColor = col

                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        supplier_accounts_to_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dataTo = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    }, year, month, day
                )
                picker.show()
                supplier_accounts_to_out.clearFocus()
            }
        }
    }

    private fun getSupplierAccounts() {
        var list:  ArrayList<ProviderInvoices> = arrayListOf()

        viewModel.providerInvoices().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    val adapterSupplierAccounts = data?.let {
                        ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it) }
                    supplier_accounts_provider_out.setAdapter(adapterSupplierAccounts)

                    list = data as ArrayList<ProviderInvoices>
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })

        supplier_accounts_provider_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        supplier_accounts_provider_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                supplier_accounts_provider_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                supplier_accounts_provider_out.clearFocus()
                providerId = list[position].id
            }
        supplier_accounts_provider_out.setOnClickListener {
            supplier_accounts_provider_out.showDropDown()
        }
        supplier_accounts_provider_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        supplier_accounts_provider_out.showDropDown()
                    }
                    if (!hasFocus && supplier_accounts_provider_out.text!!.isNotEmpty()) {
                        supplier_accounts_provider.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        supplier_accounts_provider.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        supplier_accounts_provider_out.clearFocus()
    }
}