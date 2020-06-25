package com.timelysoft.tsjdomcom.adapters.user


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.bid.UserModel
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class UserAdapter(item: ArrayList<UserModel> = arrayListOf()): GenericRecyclerAdapter<UserModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_user)
    }

    override fun bind(item: UserModel, holder: ViewHolder) {

    }
}