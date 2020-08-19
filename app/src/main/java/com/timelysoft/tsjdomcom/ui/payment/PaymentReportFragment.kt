package com.timelysoft.tsjdomcom.ui.payment

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
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.report.PaymentReportAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.payment.ListServiceModel
import com.timelysoft.tsjdomcom.service.model.payment.PaymentReportModel
import com.timelysoft.tsjdomcom.service.model.service.PeriodServiceModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import kotlinx.android.synthetic.main.fragment_create_service.*
import kotlinx.android.synthetic.main.fragment_payment_report.*
import kotlinx.android.synthetic.main.fragment_user_request.*
import java.util.*
import kotlin.collections.ArrayList

class PaymentReportFragment : Fragment() {
    private var viewModel = PaymentViewModel()
    private val myAdapter = PaymentReportAdapter()
    private var periodId: Int? = null
    private var name: String? = null
    private var mLastClickTime: Long = 0

    private var dataFrom: String = " "
    private var dataTo: String = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPaymentReportService()
        getAutoRequestFrom()
        getAutoRequestTo()
        initArgument()
    }

    private fun initArgument() {
        MainActivity.alert.show()
        viewModel.paymentInformationTsj().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    payment_report_tsj.setText(data!!.name)
                    payment_report_address.setText(data.address)
                }
                Status.ERROR, Status.NETWORK -> {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.paymentDateDefault().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    payment_report_data_from_out.setText(MyUtils.toMyDate(data!!.from))
                    payment_report_data_to_out.setText(MyUtils.toMyDate(data.to))
                    dataFrom = data.from
                    dataTo = data.to
                    initRecyclerView()
                }
                Status.ERROR, Status.NETWORK -> {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        payment_report_payment.setOnClickListener {
            MainActivity.alert.show()
            viewModel.paymentListPayment(dataFrom, dataTo, periodId)
                .observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    val data = result.data
                    when (result.status) {
                        Status.SUCCESS -> {
                            myAdapter.update(data!!)
                            myAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    }
                    MainActivity.alert.hide()
                })
        }

        payment_report_save.setOnClickListener {
            viewModel.paymentSave(dataFrom, dataTo, periodId)
                .observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    when (result.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }
    }

    private fun initRecyclerView() {
        payment_report_recycler.adapter = myAdapter
        viewModel.paymentPaymentReport(dataFrom, dataTo, periodId)
            .observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        myAdapter.update(data!!)
                        MainActivity.alert.hide()
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    private fun getAutoRequestFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        payment_report_data_from_out.keyListener = null;
        payment_report_data_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                payment_report_data_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        payment_report_data_from_out.setText(
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
                payment_report_data_from_out.clearFocus()
            }
        }
    }

    private fun getAutoRequestTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        payment_report_data_to_out.keyListener = null;
        payment_report_data_to_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                payment_report_data_to.defaultHintTextColor = col

                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        payment_report_data_to_out.setText(
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
                payment_report_data_to_out.clearFocus()
            }
        }
    }

    private fun getPaymentReportService() {
        var list: ArrayList<ListServiceModel> = arrayListOf()
        viewModel.paymentListService()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterPaymentReportService = data?.let {
                            ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                        }
                        payment_report_tape_out.setAdapter(adapterPaymentReportService)
                        list = data as ArrayList<ListServiceModel>
                    }
                    Status.NETWORK, Status.ERROR -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        payment_report_tape_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        payment_report_tape_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                payment_report_tape_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                payment_report_tape_out.clearFocus()
                periodId = list[position].id
            }
        payment_report_tape_out.setOnClickListener {
            payment_report_tape_out.showDropDown()
        }
        payment_report_tape_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        payment_report_tape_out.showDropDown()
                    }
                    if (!hasFocus && payment_report_tape_out.text!!.isNotEmpty()) {
                        payment_report_tape.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        payment_report_tape.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        payment_report_tape_out.clearFocus()
    }

    override fun onStart() {
        super.onStart()
        hintText()
    }

    private fun hintText() {
        payment_report_data_from.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        payment_report_data_to.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }
}