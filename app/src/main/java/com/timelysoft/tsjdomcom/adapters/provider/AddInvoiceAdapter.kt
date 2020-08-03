package com.timelysoft.tsjdomcom.adapters.provider

import android.graphics.drawable.BitmapDrawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.provider.FileModel
import kotlinx.android.synthetic.main.item_add_invoice.view.*


class AddInvoiceAdapter (item: ArrayList<FileModel> = arrayListOf()): GenericRecyclerAdapter<FileModel>(item){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_add_invoice)
    }

    override fun bind(item: FileModel, holder: ViewHolder) {
        holder.itemView.add_invoice_name.text = item.name

        holder.itemView.add_invoice_delete.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, items.size)
        }

        Glide
            .with(holder.itemView.add_invoice_file)
            .load(item.link)
            .into(holder.itemView.add_invoice_file)
    }
}