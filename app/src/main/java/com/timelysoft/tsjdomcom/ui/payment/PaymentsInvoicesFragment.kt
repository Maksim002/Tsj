package com.timelysoft.tsjdomcom.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_payments_invoices.*
import kotlinx.android.synthetic.main.fragment_payments_invoices.payments_invoices_payment

class PaymentsInvoicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payments_invoices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        iniArguments()
    }

    private fun iniArguments() {
        payments_invoices_payment.setOnClickListener {
            findNavController().navigate(R.id.navigation_payment)
        }
        payment_invoices_report.setOnClickListener {
            findNavController().navigate(R.id.navigation_payment_report)
        }
        payment_invoices_templates.setOnClickListener {
            findNavController().navigate(R.id.navigation_templates)
        }
        payments_invoices_issued.setOnClickListener {
            findNavController().navigate(R.id.navigation_invoices_issued)
        }
    }
}