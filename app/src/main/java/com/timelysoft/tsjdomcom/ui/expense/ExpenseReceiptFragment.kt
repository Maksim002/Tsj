package com.timelysoft.tsjdomcom.ui.expense

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.expense.ExpensesReceiptPager
import com.timelysoft.tsjdomcom.adapters.news.NewsAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTSJModel
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTypeModel
import com.timelysoft.tsjdomcom.service.model.news.NewsModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_expense_receipts.*

class ExpenseReceiptFragment : Fragment() {
    private var viewModel = ExpenseViewModel()
    private var positionType: Int = 0
    private var list: ArrayList<ExpenseListTypeModel> = arrayListOf()
    val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_receipts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAddAddress()
        initArgument()
    }

    override fun onStart() {
        super.onStart()
        if (positionType != 0){
            expense_layout_recycler.visibility = View.VISIBLE
        }
        initPager()
    }

    private fun initArgument() {
        expanse_receipts_add_entry.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_entry)
        }

        viewModel.expenseListType().observe(viewLifecycleOwner, Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    list = data!!
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
            MainActivity.alert.hide()
        })
    }

    private fun initPager() {
        val fragmentAdapter = ExpensesReceiptPager(positionType, childFragmentManager)
        expenses_receipts_pager.adapter = fragmentAdapter
        expenses_receipts_tab.setupWithViewPager(expenses_receipts_pager)
    }

    private fun getAddAddress() {
        MainActivity.alert.show()
        var list: ArrayList<ExpenseListTSJModel> = arrayListOf()
        viewModel.expenseListTSJ().observe(viewLifecycleOwner, Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    val adapterAddAddress = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, data!!)
                    expense_receipts_address_out.setAdapter(adapterAddAddress)
                    list = data
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
            MainActivity.alert.hide()
        })

        expense_receipts_address_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        expense_receipts_address_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                expense_receipts_address_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                expense_receipts_address_out.clearFocus()
                positionType = list[position].id!!
                expense_layout_recycler.visibility = View.VISIBLE
                initPager()
            }
        expense_receipts_address_out.setOnClickListener {
            expense_receipts_address_out.showDropDown()
        }
        expense_receipts_address_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    expense_receipts_address_out.showDropDown()
                }
                if (!hasFocus && expense_receipts_address_out.text!!.isNotEmpty()) {
                    expense_receipts_address.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    expense_receipts_address.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        expense_receipts_address_out.clearFocus()
    }
}