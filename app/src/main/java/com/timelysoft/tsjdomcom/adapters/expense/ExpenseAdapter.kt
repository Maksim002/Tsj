package com.timelysoft.tsjdomcom.adapters.expense

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class ExpenseAdapter (item: ArrayList<ExpenseModel> = arrayListOf()): GenericRecyclerAdapter<ExpenseModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_expense)
    }

    override fun bind(item: ExpenseModel, holder: ViewHolder) {
    }
}