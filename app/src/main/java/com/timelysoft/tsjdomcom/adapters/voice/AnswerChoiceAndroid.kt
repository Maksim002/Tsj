package com.timelysoft.tsjdomcom.adapters.voice

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_answer_choice.view.*
import okhttp3.internal.notify
import kotlin.collections.ArrayList
import kotlin.collections.Collection as Collection1

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
           notifyItemRemoved(holder.adapterPosition);
           notifyItemRangeChanged(holder.adapterPosition, holder.adapterPosition)
        }

        holder.itemView.answer_choice_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    item.options = holder.itemView.answer_choice_name.text.toString()
                } catch (e: Exception) {
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}