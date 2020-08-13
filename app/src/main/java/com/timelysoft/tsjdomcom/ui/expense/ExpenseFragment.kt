package com.timelysoft.tsjdomcom.ui.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.expense.ExpenseAdapter
import com.timelysoft.tsjdomcom.adapters.expense.ExpenseClickListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.expense.SlipModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_comings.*
import kotlinx.android.synthetic.main.fragment_expense.*

class ExpenseFragment(var positionType: Int) : Fragment(), ExpenseClickListener {
    private var viewModel = ExpenseViewModel()
    private var myAdapter = ExpenseAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        MainActivity.alert.show()
        expense_recycler.adapter = myAdapter
        viewModel.expenseExpensesReceipts(positionType).observe(viewLifecycleOwner, Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    myAdapter.update(data!!.slips as ArrayList<SlipModel>)
                    MainActivity.alert.hide()
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun expenseClickListener(item: SlipModel) {
        findNavController().navigate(R.id.navigation_change)
    }
}