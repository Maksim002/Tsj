package com.timelysoft.tsjdomcom.adapters.provider

import android.graphics.Bitmap
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.provider.FileModel
import com.timelysoft.tsjdomcom.ui.provider.AddInvoiceFragment
import kotlinx.android.synthetic.main.item_add_invoice.view.*


class AddInvoiceAdapter(
    var listener: AddInvoiceListener,
    var item: ArrayList<FileModel> = arrayListOf()
) : GenericRecyclerAdapter<FileModel>(item) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_add_invoice)
    }

    override fun bind(item: FileModel, holder: ViewHolder) {
        holder.itemView.add_invoice_name.text = item.name

        holder.itemView.add_invoice_delete.setOnClickListener {
            listener.clearClickListener(holder.adapterPosition, item)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, items.size)
        }

        Glide.with(holder.itemView.add_invoice_file)
            .asBitmap()
            .load(item.link)
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    holder.itemView.add_invoice_file.setImageBitmap(resource)
                    AddInvoiceFragment.myImage.put(item.name, resource)
                    holder.itemView.add_invoice_file
                    //отправляй этот файл
                }
            })
    }
}