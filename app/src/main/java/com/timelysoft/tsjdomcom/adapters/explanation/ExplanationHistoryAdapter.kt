package com.timelysoft.tsjdomcom.adapters.explanation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder

class ExplanationHistoryAdapter (item: ArrayList<ExplanationHistoryModel> = arrayListOf()): GenericRecyclerAdapter<ExplanationHistoryModel>(item){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_explanation_history)
    }

    override fun bind(item: ExplanationHistoryModel, holder: ViewHolder) {

    }

}