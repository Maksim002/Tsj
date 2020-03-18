package com.example.tsj.adapters.vote

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.VoteModel
import kotlinx.android.synthetic.main.item_vote.view.*

class VoteAdapter(listener: VoteItemClickListener, item: List<VoteModel>) :
    GenericRecyclerAdapter<VoteModel>(item) {
    private val listener: VoteItemClickListener = listener
    override fun bind(item: VoteModel, holder: ViewHolder) {
        holder.itemView.vote_content.text = item.question
        holder.itemView.setOnClickListener { listener.onVoteItemClicked(item) }

        if (!item.isCanVote) {
            holder.itemView.vote_btn.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_vote)
    }
}