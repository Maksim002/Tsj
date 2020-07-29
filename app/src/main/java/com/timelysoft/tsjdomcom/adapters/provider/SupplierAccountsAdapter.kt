package com.timelysoft.tsjdomcom.adapters.provider

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class SupplierAccountsAdapter (item: ArrayList<SupplierAccountsModel> = arrayListOf()): GenericRecyclerAdapter<SupplierAccountsModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_supplier_accounts)
    }

    override fun bind(item: SupplierAccountsModel, holder: ViewHolder) {

    }
}