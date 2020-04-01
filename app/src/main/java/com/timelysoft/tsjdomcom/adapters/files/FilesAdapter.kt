package com.timelysoft.tsjdomcom.adapters.files

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_manadger.view.*

class FilesAdapter(
    private val listener: GeneralClickListener,
    items: ArrayList<FilesModel> = ArrayList()
) : GenericRecyclerAdapter<FilesModel>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_manadger)
    }

    override fun bind(item: FilesModel, holder: ViewHolder) {
        holder.itemView.textName.text = item.text
        if (item.url.isNotEmpty()) {
            holder.itemView.imageId.setImageResource(R.drawable.ic_save)
        }else{
            holder.itemView.imageId.setImageResource(R.drawable.ic_clear_s)
        }
        holder.itemView.imageId.setOnClickListener {
            listener.onClickItem(holder.adapterPosition, item.url)
        }
    }

}