package com.timelysoft.tsjdomcom.adapters.residence

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_references_residence.view.*

class ReferencesResidenceAdapter (item: ArrayList<ReferencesResidenceModel> = arrayListOf()): GenericRecyclerAdapter<ReferencesResidenceModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_references_residence)
    }

    override fun bind(item: ReferencesResidenceModel, holder: ViewHolder) {
        holder.itemView.references_residence_change.setOnClickListener {
            it.findNavController().navigate(R.id.navigation_residence_change)
        }
    }
}