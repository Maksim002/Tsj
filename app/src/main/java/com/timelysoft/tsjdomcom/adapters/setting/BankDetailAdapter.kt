package com.timelysoft.tsjdomcom.adapters.setting

import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_bank_detail.view.*

class BankDetailAdapter (item: ArrayList<BankDetailModel> = arrayListOf()): GenericRecyclerAdapter<BankDetailModel>(item){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_bank_detail)
    }

    override fun bind(item: BankDetailModel, holder: ViewHolder) {
        holder.itemView.bank_details_edit.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("one", 1)
            it.findNavController().navigate(R.id.navigation_add_bank, bundle)
        }
    }
}