package com.timelysoft.tsjdomcom.adapters.voice

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_voting.view.*

class VotingAdapter (var listener: VotingListener, item: ArrayList<VotingModel> = arrayListOf()): GenericRecyclerAdapter<VotingModel>(item){

    private var index: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_voting)
    }

    override fun bind(item: VotingModel, holder: ViewHolder) {

        holder.itemView.voting_choice.setOnClickListener {
            index = holder.adapterPosition
            notifyDataSetChanged()
            listener.onVotingClickListener(item)
        }

        if (index == holder.adapterPosition){
            holder.itemView.voting_choice.setBackgroundColor(Color.parseColor("#FF9116"));
        }else{
            holder.itemView.voting_choice.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }
    }
}