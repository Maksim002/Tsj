package com.example.tsj.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.model.PersonalModel
import com.example.tsj.service.model.InvoicesAccounts

class PersonalAdapter: RecyclerView.Adapter<PersonalViewHolder>() {

    private var model: List<InvoicesAccounts> = ArrayList()

    fun submitList(list: List<InvoicesAccounts>) {
        model = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolder {
        return PersonalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: PersonalViewHolder, position: Int) {
        holder.bind(model.get(position));
    }
}