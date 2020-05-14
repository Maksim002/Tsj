package com.timelysoft.tsjdomcom.adapters.owners

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_at_home.view.*

class OwnersAdapter (items: ArrayList<OwnersModel> = ArrayList()): GenericRecyclerAdapter<OwnersModel>(items){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_at_home)
    }

    override fun bind(item: OwnersModel, holder: ViewHolder) {
        holder.itemView.at_home_title.text = item.title
        holder.itemView.at_home_name.text = item.name
        holder.itemView.at_home_address_text.text = item.address
        holder.itemView.at_home_block.text = item.block
        holder.itemView.at_home_registration.text = item.registration
        holder.itemView.at_home_mail.text = item.mail

        if (holder.itemView.at_home_registration.text == ""){
            holder.itemView.at_home_registration.visibility = View.GONE
        }else{
            holder.itemView.at_home_registration.visibility = View.VISIBLE
        }

        if (holder.itemView.at_home_mail.text == ""){
            holder.itemView.at_home_mail.visibility = View.GONE
        }else{
            holder.itemView.at_home_mail.visibility = View.VISIBLE
        }
    }
}