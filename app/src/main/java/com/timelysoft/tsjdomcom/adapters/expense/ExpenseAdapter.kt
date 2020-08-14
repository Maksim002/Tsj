package com.timelysoft.tsjdomcom.adapters.expense

import android.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.expense.SlipModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_comings.view.*
import kotlinx.android.synthetic.main.item_expense.view.*

class ExpenseAdapter (var listener: ExpenseClickListener, item: ArrayList<SlipModel> = arrayListOf()): GenericRecyclerAdapter<SlipModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_expense)
    }

    override fun bind(item: SlipModel, holder: ViewHolder) {
        if (item.amountType != 1){
            holder.itemView.expense_layout.visibility = View.GONE
        }
        holder.itemView.expense_provider.text = item.managerName
        holder.itemView.expense_coming.text = item.typeName
        holder.itemView.expense_sum.text = item.amount.toString()
        holder.itemView.expense_data.text = MyUtils.toMyDate(item.onDate!!)
        holder.itemView.expense_description.text = item.description

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