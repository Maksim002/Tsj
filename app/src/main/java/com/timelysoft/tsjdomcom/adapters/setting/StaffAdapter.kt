package com.timelysoft.tsjdomcom.adapters.setting

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_staff.view.*

class StaffAdapter (item: ArrayList<StaffModel> = arrayListOf()): GenericRecyclerAdapter<StaffModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_staff)
    }

    override fun bind(item: StaffModel, holder: ViewHolder) {
        holder.itemView.staff_edit.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("one", 1)
            it.findNavController().navigate(R.id.navigation_staff_add, bundle)
        }
    }
}