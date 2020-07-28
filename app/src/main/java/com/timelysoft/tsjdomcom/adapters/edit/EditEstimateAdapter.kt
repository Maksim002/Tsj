package com.timelysoft.tsjdomcom.adapters.edit

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_edit_estemate.view.*

class EditEstimateAdapter(var date: ArrayList<EditEstimateModel> = arrayListOf()): GenericRecyclerAdapter<EditEstimateModel>(date){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_edit_estemate)
    }

    override fun bind(item: EditEstimateModel, holder: ViewHolder) {
        val myAdapter = EditEstimateTemplatesAdapter()
        val date: ArrayList<EditEstimateTemplatesModel> = arrayListOf()
        holder.itemView.edit_estimate_templates_recycler.adapter = myAdapter

        holder.itemView.edit_estimate_add_title.setOnClickListener {
            date.add(EditEstimateTemplatesModel("", ""))
            myAdapter.update(date)
            myAdapter.notifyItemRangeChanged(date.size, date.size)
        }

        holder.itemView.edit_estimate_clear_title.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, date.size)
        }
    }
}