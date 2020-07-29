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
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.SupplierAccountsAdapter
import com.timelysoft.tsjdomcom.adapters.provider.SupplierAccountsModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_add_counter_all.*
import kotlinx.android.synthetic.main.fragment_supplier_accounts.*
import kotlinx.android.synthetic.main.fragment_user_request.*
import java.util.*
import kotlin.collections.ArrayList

class SupplierAccountsFragment : Fragment() {
    private val myAdapter = SupplierAccountsAdapter()

    private var mLastClickTime: Long = 0

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
    }

    private fun initRecycler() {
        val list: ArrayList<SupplierAccountsModel> = arrayListOf()
        list.add(SupplierAccountsModel(""))
        list.add(SupplierAccountsModel(""))
        list.add(SupplierAccountsModel(""))
        list.add(SupplierAccountsModel(""))

        myAdapter.update(list)
        supplier_accounts_recycler.adapter = myAdapter
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
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            supplier_accounts_from_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day)
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

                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            supplier_accounts_to_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day
                    )
                picker.show()
                supplier_accounts_to_out.clearFocus()
            }
        }
    }

    private fun getSupplierAccounts() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterSupplierAccounts = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        supplier_accounts_provider_out.setAdapter(adapterSupplierAccounts)

        supplier_accounts_provider_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        supplier_accounts_provider_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                supplier_accounts_provider_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                supplier_accounts_provider_out.clearFocus()
            }
        supplier_accounts_provider_out.setOnClickListener {
            supplier_accounts_provider_out.showDropDown()
        }
        supplier_accounts_provider_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
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