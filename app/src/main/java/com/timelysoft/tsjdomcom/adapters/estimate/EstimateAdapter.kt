package com.timelysoft.tsjdomcom.adapters.estimate

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_estimate.view.*

class EstimateAdapter (var listener: EstimateClickListener, item: ArrayList<EstimateModel> = arrayListOf()):  GenericRecyclerAdapter<EstimateModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_estimate)
    }

    override fun bind(item: EstimateModel, holder: ViewHolder) {
        holder.itemView.setOnClickListener {
            listener.estimateClickListener(item)
        }
        holder.itemView.estimate_change.setOnClickListener {
            it.findNavController().navigate(R.id.navigation_edit_estimate)
        }
    }
}