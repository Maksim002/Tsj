package com.timelysoft.tsjdomcom.adapters.request

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.request.UserRequestModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.item_user_request.view.*

class UserRequestAdapter (var listener: UserRequestListener , item: ArrayList<UserRequestModel> = arrayListOf()): GenericRecyclerAdapter<UserRequestModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_user_request)
    }

    override fun bind(item: UserRequestModel, holder: ViewHolder) {
        holder.itemView.user_request_address.text = item.shortAddress
        holder.itemView.user_request_data_from.text = MyUtils.toMyDate(item.createdDate)
        holder.itemView.user_request_status.text = item.statusName
        holder.itemView.user_request_data_to.text = MyUtils.toMyDate(item.statusDate)
        holder.itemView.user_request_text_name.text = item.description

        if (item.isEditable != false){
            holder.itemView.user_request_view.visibility = View.VISIBLE
        }else{
            holder.itemView.user_request_view.visibility = View.GONE
        }

        holder.itemView.user_request_save.setOnClickListener {
            listener.userRequestClick(holder.adapterPosition)
        }

        holder.itemView.user_request_link_owner.setOnClickListener {
            listener.userRequestClickOwner(item.id, holder.adapterPosition)
        }

        holder.itemView.user_request_view.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("userRequestId", item.id)
            it.findNavController().navigate(R.id.view_user_fragment, bundle)
        }
    }
}