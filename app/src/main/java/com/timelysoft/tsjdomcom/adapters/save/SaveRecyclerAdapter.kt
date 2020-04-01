package com.timelysoft.tsjdomcom.adapters.save

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.SaveModel
import kotlinx.android.synthetic.main.item_save.view.*

class SaveRecyclerAdapter(item: ArrayList<SaveModel>, listener: SaveListener): GenericRecyclerAdapter<SaveModel>(item){
    private var listener: SaveListener
    private lateinit var layout: LinearLayout

    init {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_save)
    }



    override fun bind(item: SaveModel, holder: ViewHolder) {
        holder.itemView.title.text = item.title
        holder.itemView.count.text = item.count

        layout = holder.itemView.findViewById(R.id.saveLayout)
        layout.setOnClickListener {
                listener.onClick(item)
        }
    }
}