package com.timelysoft.tsjdomcom.adapters.vote

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import com.timelysoft.tsjdomcom.service.model.VoteModel
import kotlinx.android.synthetic.main.item_vote.view.*

class VoteAdapter(private val listener: VoteItemClickListener, item: ArrayList<VoteModel>) :
    GenericRecyclerAdapter<VoteModel>(item) {
    override fun bind(item: VoteModel, holder: ViewHolder) {
        holder.itemView.vote_content.text = item.question
        holder.itemView.setOnClickListener { listener.onClick(item,false) }
        holder.itemView.vote_btn.setOnClickListener { listener.onClick(item,true) }

        if (!item.isCanVote) {
            holder.itemView.vote_btn.visibility = View.GONE
            holder.itemView.vote_title.text = "Вы уже проголосовали"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_vote)
    }
}