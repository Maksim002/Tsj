package com.timelysoft.tsjdomcom.adapters.voice

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
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
            notifyItemRemoved(holder.adapterPosition);
            notifyItemRangeChanged(holder.adapterPosition, items.size - holder.adapterPosition)
        }

//        holder.itemView.answer_choice_name.setOnFocusChangeListener { v, hasFocus ->
//            item.options = holder.itemView.answer_choice_name.text.toString()
//        }

        holder.itemView.answer_choice_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val name = holder.itemView.answer_choice_name.text.toString()

                item.options = name
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}