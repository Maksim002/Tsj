package com.timelysoft.tsjdomcom.adapters.edit

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.nested.EditEstimateNestedAdapter
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_edit_estemate.view.*

class EditEstimateAdapter(var listener: EditEstimateListener, var date: ArrayList<EditEstimateModel> = arrayListOf()) : GenericRecyclerAdapter<EditEstimateModel>(date) {
    private var myAdapter = EditEstimateNestedAdapter()
    val list: ArrayList<EstimateComponentModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_edit_estemate)
    }

    override fun bind(item: EditEstimateModel, holder: ViewHolder) {
        holder.itemView.edit_estimate_clear.setOnClickListener {
            listener.editEstimateClickListener(item, holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, date.size)
            holder.itemView.edit_estimate_name_out.setText("")
        }
        holder.itemView.edit_estimate_recycler.adapter = myAdapter

        holder.itemView.edit_estimate_add.setOnClickListener {
            list.add(EstimateComponentModel("",""))
            myAdapter.update(list)
        }
    }
}

