package com.timelysoft.tsjdomcom.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.history.PaymentHistoryAdapter
import com.timelysoft.tsjdomcom.adapters.history.PaymentHistoryModel
import kotlinx.android.synthetic.main.fragment_payment_history.*

class PaymentHistoryFragment : Fragment() {
    private val myAdapter = PaymentHistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {

        val list: ArrayList<PaymentHistoryModel> = arrayListOf()
        list.add(PaymentHistoryModel(""))
        list.add(PaymentHistoryModel(""))
        list.add(PaymentHistoryModel(""))
        list.add(PaymentHistoryModel(""))

        myAdapter.update(list)
        payment_history_recycler.adapter = myAdapter
    }
}