package com.timelysoft.tsjdomcom.ui.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.expense.ComingsModel
import com.timelysoft.tsjdomcom.adapters.expense.ExpenseAdapter
import com.timelysoft.tsjdomcom.adapters.expense.ExpenseClickListener
import kotlinx.android.synthetic.main.fragment_expense.*

class ExpenseFragment : Fragment(), ExpenseClickListener {
    private var myAdapter = ExpenseAdapter(this)
    private val list: ArrayList<ComingsModel> = arrayListOf()

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
        list.add(ComingsModel("Puma"))
        list.add(ComingsModel("Panda"))
        list.add(ComingsModel("add"))
        list.add(ComingsModel("da da"))

        myAdapter.update(list)
        expense_recycler.adapter = myAdapter
    }

    override fun expenseClickListener(item: ComingsModel) {
        val bundle = Bundle()
        bundle.putSerializable("comings", list)
        findNavController().navigate(R.id.navigation_change, bundle)
    }
}