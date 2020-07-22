package com.timelysoft.tsjdomcom.adapters.setting

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_schedule_works.view.*

class ScheduleWorksAdapter(item: ArrayList<ScheduleWorksModel> = arrayListOf()): GenericRecyclerAdapter<ScheduleWorksModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_schedule_works)
    }

    override fun bind(item: ScheduleWorksModel, holder: ViewHolder) {
        holder.itemView.schedule_works_edit.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("one", 1)
            it.findNavController().navigate(R.id.navigation_schedule_works_add, bundle)
        }
    }
}