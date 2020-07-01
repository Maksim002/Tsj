package com.timelysoft.tsjdomcom.adapters.request

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class UserRequestAdapter (item: ArrayList<UserRequestModel> = arrayListOf()): GenericRecyclerAdapter<UserRequestModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_user_request)
    }

    override fun bind(item: UserRequestModel, holder: ViewHolder) {

    }

}