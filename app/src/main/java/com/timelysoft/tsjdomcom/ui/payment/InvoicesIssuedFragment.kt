package com.timelysoft.tsjdomcom.ui.payment

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.lssued.InvoicesIssuedAdapter
import com.timelysoft.tsjdomcom.adapters.lssued.InvoicesLssuedModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_invoices_issued.*
import java.util.*
import kotlin.collections.ArrayList

class InvoicesIssuedFragment : Fragment() {

    private var myAdapter = InvoicesIssuedAdapter()

    private var mLastClickTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invoices_issued, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAutoDatesFrom()
        getAutoDatesTo()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list:  ArrayList<InvoicesLssuedModel> = arrayListOf()
        list.add(InvoicesLssuedModel(""))
        list.add(InvoicesLssuedModel(""))
        list.add(InvoicesLssuedModel(""))
        list.add(InvoicesLssuedModel(""))

        myAdapter.update(list)
        invoices_issued_recycler.adapter = myAdapter
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        invoices_issued_date_from_out.keyListener = null;
        invoices_issued_date_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                invoices_issued_date_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            invoices_issued_date_from_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day)
                picker.show()
                invoices_issued_date_from_out.clearFocus()
            }
        }
    }
    private fun getAutoDatesTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        invoices_issued_date_to_out.keyListener = null;
        invoices_issued_date_to_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                invoices_issued_date_to.defaultHintTextColor = col

                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            invoices_issued_date_to_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day
                    )
                picker.show()
                invoices_issued_date_to_out.clearFocus()
            }
        }
    }
}