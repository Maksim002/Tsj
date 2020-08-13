package com.timelysoft.tsjdomcom.adapters.expense

import com.timelysoft.tsjdomcom.service.model.expense.SlipModel

interface ExpenseClickListener {
    fun expenseClickListener(item : SlipModel)
}