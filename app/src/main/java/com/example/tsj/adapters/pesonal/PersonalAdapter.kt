package com.example.tsj.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.model.PersonalModel

class PersonalAdapter: RecyclerView.Adapter<PersonalViewHolder>() {

    private var model: List<PersonalModel> = ArrayList()

    fun submitList(list: List<PersonalModel>) {
        model = list
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