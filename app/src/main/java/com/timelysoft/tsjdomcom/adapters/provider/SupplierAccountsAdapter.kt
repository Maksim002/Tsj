package com.timelysoft.tsjdomcom.adapters.provider

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.provider.SupplierAccountsModel
import kotlinx.android.synthetic.main.item_supplier_accounts.view.*

class SupplierAccountsAdapter (item: ArrayList<SupplierAccountsModel> = arrayListOf()): GenericRecyclerAdapter<SupplierAccountsModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_supplier_accounts)
    }

    override fun bind(item: SupplierAccountsModel, holder: ViewHolder) {
        holder.itemView.supplier_accounts_name.text = item.providerName
        holder.itemView.supplier_accounts_name_service.text = item.service
        holder.itemView.supplier_accounts_data.text = item.date
        try {
            holder.itemView.supplier_accounts_counter.text = item.countersValue.toString()
            holder.itemView.supplier_accounts_sum.text = item.paymentAmount.toString()
        }catch (e: Exception){

        }

    }
}