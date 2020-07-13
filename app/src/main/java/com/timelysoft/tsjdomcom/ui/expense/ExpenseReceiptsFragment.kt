package com.timelysoft.tsjdomcom.ui.expense

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R

class ExpensesReceiptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses_receipts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
    }

    private fun initArgument() {

    }
}