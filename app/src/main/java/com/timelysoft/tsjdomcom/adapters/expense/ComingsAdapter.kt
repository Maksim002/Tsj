package com.timelysoft.tsjdomcom.adapters.expense

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_comings.view.*

class ComingsAdapter (var listener: ComingsClickListener,item: ArrayList<ComingsModel> = arrayListOf()): GenericRecyclerAdapter<ComingsModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_comings)
    }

    override fun bind(item: ComingsModel, holder: ViewHolder) {
        holder.itemView.comings_debate.text = item.address

        holder.itemView.comings_change.setOnClickListener {
            listener.comingsOnClickListener(item)
        }
    }
}