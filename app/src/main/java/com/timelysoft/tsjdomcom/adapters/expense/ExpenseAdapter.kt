package com.timelysoft.tsjdomcom.adapters.expense

import android.app.AlertDialog
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_expense.view.*

class ExpenseAdapter (var listener: ExpenseClickListener, item: ArrayList<ComingsModel> = arrayListOf()): GenericRecyclerAdapter<ComingsModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_expense)
    }

    override fun bind(item: ComingsModel, holder: ViewHolder) {
        holder.itemView.expense_change.setOnClickListener {
            listener.expenseClickListener(item)
        }

        holder.itemView.expense_delete.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            builder.setMessage("Действительно ли вы\n" + "желаете удалить элемент?")

            builder.setPositiveButton("Да") { dialog, which ->
            }

            builder.setNegativeButton("Нет") { dialog, which ->
            }
            builder.show()
        }
    }
}