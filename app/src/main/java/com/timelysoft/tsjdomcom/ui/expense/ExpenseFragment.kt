package com.timelysoft.tsjdomcom.ui.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.expense.ExpenseAdapter
import com.timelysoft.tsjdomcom.adapters.expense.ExpenseModel
import kotlinx.android.synthetic.main.fragment_expense.*

class ExpenseFragment : Fragment() {
    private var myAdapter = ExpenseAdapter()

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
        val list: ArrayList<ExpenseModel> = arrayListOf()
        list.add(ExpenseModel(""))
        list.add(ExpenseModel(""))
        list.add(ExpenseModel(""))
        list.add(ExpenseModel(""))

        myAdapter.update(list)
        expense_recycler.adapter = myAdapter
    }
}