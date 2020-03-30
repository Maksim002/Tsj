package com.example.tsj.adapters.vote

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.vote.VotingVariantsModel
import kotlinx.android.synthetic.main.item_vote_detail.view.*


class VoteDetailAdapter(item: List<VotingVariantsModel>) :

    GenericRecyclerAdapter<VotingVariantsModel>(item) {

    override fun bind(item: VotingVariantsModel, holder: ViewHolder) {
        holder.itemView.vote_detail_precent.text = item.percent.toString() + "%"
        holder.itemView.vote_detail_name.text = item.name

        if (item.percent == 0.0){
            holder.itemView.vote_detail_progress.progress = 0
        }else{
            holder.itemView.vote_detail_progress.progress = item.percent.toInt() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_vote_detail)
    }
}