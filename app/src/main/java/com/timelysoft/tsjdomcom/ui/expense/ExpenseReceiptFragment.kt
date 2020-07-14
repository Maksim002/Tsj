package com.timelysoft.tsjdomcom.ui.expense


import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.expense.ExpensesReceiptPager
import kotlinx.android.synthetic.main.fragment_expense_receipts.*


class ExpenseReceiptFragment : Fragment() {

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
        initPager()
        getAddAddress()
        initArgument()
    }

    private fun initArgument() {
        expanse_receipts_add_entry.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_entry)
        }
    }

    private fun initPager() {
        val fragmentAdapter = ExpensesReceiptPager(childFragmentManager)
        expenses_receipts_pager.adapter = fragmentAdapter
        expenses_receipts_tab.setupWithViewPager(expenses_receipts_pager)
    }

    private fun getAddAddress() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddAddress = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        expense_receipts_address_out.setAdapter(adapterAddAddress)

        expense_receipts_address_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        expense_receipts_address_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                expense_receipts_address_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                expense_receipts_address_out.clearFocus()
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