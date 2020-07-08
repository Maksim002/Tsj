package com.timelysoft.tsjdomcom.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.report.PaymentReportAdapter
import com.timelysoft.tsjdomcom.adapters.report.PaymentReportModel
import kotlinx.android.synthetic.main.fragment_payment_report.*

class PaymentReportFragment : Fragment() {

    private val myAdapter = PaymentReportAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<PaymentReportModel> = arrayListOf()
        list.add(PaymentReportModel(""))
        list.add(PaymentReportModel(""))
        list.add(PaymentReportModel(""))
        list.add(PaymentReportModel(""))

        myAdapter.update(list)
        payment_report_recycler.adapter = myAdapter
    }
}