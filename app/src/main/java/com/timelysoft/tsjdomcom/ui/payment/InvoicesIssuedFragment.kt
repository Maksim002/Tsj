package com.timelysoft.tsjdomcom.ui.payment

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.lssued.InvoicesIssuedAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.payment.InvoicesIssuedModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_invoices_issued.*
import java.util.*


class InvoicesIssuedFragment : Fragment() {
    private var viewModel = PaymentViewModel()
    private var myAdapter = InvoicesIssuedAdapter()

    private var mLastClickTime: Long = 0
    private var dataFrom: String = ""
    private var dataTo: String = ""

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
        initArgument()
    }

    private fun initArgument() {
        MainActivity.alert.show()
        viewModel.paymentDefaultPeriod().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    dataFrom = data!!.from
                    dataTo = data.to
                    invoices_issued_date_from_out.setText(MyUtils.toMyDate(dataFrom))
                    invoices_issued_date_to_out.setText(MyUtils.toMyDate(dataTo))
                    initRecyclerView()
                }
                Status.NETWORK, Status.ERROR->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
            MainActivity.alert.hide()
        })

    }

    private fun initRecyclerView() {
        MainActivity.alert.show()
        invoices_issued_recycler.adapter = myAdapter
        viewModel.invoicesIssued(dataFrom, dataTo).observe(viewLifecycleOwner, androidx.lifecycle.Observer {result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    myAdapter.update(data as ArrayList<InvoicesIssuedModel>)
                    myAdapter.notifyDataSetChanged()
                }
                Status.NETWORK, Status.ERROR->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
            MainActivity.alert.hide()
        })
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
                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            invoices_issued_date_from_out.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear + 1,
                                    year1
                                )
                            )

                        dataFrom = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                        initRecyclerView()
                    }, year, month, day
                )
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
                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        invoices_issued_date_to_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dataTo = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                        initRecyclerView()
                    }, year, month, day
                )
                picker.show()
                invoices_issued_date_to_out.clearFocus()
            }
        }
    }
}