package com.timelysoft.tsjdomcom.adapters.expense

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.expense.SlipModel
import com.timelysoft.tsjdomcom.ui.expense.ComingsFragment
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_comings.view.*

class ComingsAdapter (var listener: ComingsClickListener,item: ArrayList<SlipModel> = arrayListOf()): GenericRecyclerAdapter<SlipModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_comings)
    }

    override fun bind(item: SlipModel, holder: ViewHolder) {
        if (item.amountType != 2){
            holder.itemView.comings_layout.visibility = View.GONE
        }
        holder.itemView.comings_name.text = item.managerName
        holder.itemView.comings_coming.text = item.typeName
        holder.itemView.comings_sum.text = item.amount.toString()
        holder.itemView.comings_date.text = MyUtils.toMyDate(item.onDate!!)
        holder.itemView.comings_debate.text = item.description

        holder.itemView.comings_change.setOnClickListener {
            listener.comingsOnClickListener(item)
        }

        holder.itemView.comings_delete.setOnClickListener {
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