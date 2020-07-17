package com.timelysoft.tsjdomcom.adapters.voice

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_answer_choice.view.*

class AnswerChoiceAndroid(var date: ArrayList<AnswerChoiceModel> = arrayListOf()) :
    GenericRecyclerAdapter<AnswerChoiceModel>(date) {

    var position: ArrayList<AnswerChoiceModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_answer_choice)
    }

    override fun bind(item: AnswerChoiceModel, holder: ViewHolder) {
        holder.itemView.answer_choice_name.setText(item.options)
        holder.itemView.answer_choice_position.text = holder.adapterPosition.plus(1).toString()

        holder.itemView.answer_choice_delete.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, items.size - holder.adapterPosition)
            notifyDataSetChanged()
        }

        holder.itemView.answer_choice_name.setOnFocusChangeListener { v, hasFocus ->
                item.options = holder.itemView.answer_choice_name.text.toString()
        }
    }
}