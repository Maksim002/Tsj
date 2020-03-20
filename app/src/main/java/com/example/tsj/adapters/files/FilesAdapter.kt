package com.example.tsj.adapters.files

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
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
        holder.itemView.imageId.setOnClickListener {
            listener.onClickItem(holder.adapterPosition)
        }
    }

}