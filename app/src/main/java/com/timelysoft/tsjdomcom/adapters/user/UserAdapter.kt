package com.timelysoft.tsjdomcom.adapters.user


import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.user.UserChairmanModel
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.item_user.view.user_to_change

class UserAdapter(item: ArrayList<UserChairmanModel> = arrayListOf()): GenericRecyclerAdapter<UserChairmanModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_user)
    }

    override fun bind(item: UserChairmanModel, holder: ViewHolder) {
        holder.itemView.user_name.text = item.name
        holder.itemView.user_email.text = item.email
        holder.itemView.user_registration.text = item.roleType

        holder.itemView.user_to_change.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("editId", item.id)
            it.findNavController().navigate(R.id.navigation_edit, bundle)
        }
    }
}