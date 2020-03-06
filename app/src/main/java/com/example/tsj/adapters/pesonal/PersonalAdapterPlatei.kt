package com.example.tsj.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.service.model.InvoicesAccounts
import com.example.tsj.ui.personal.PersonalListener

class PersonalAdapterPlatei(listene: PersonalListener) :
    RecyclerView.Adapter<PersonalViewHolderPlatei>() {

    private var model: List<InvoicesAccounts> = ArrayList()
    private var listene: PersonalListener

    init {
        this.listene = listene
    }


    fun submitList(list: List<InvoicesAccounts>) {
        model = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolderPlatei {
        return PersonalViewHolderPlatei(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal, parent, false),
            listene
        )
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holderPlatei: PersonalViewHolderPlatei, position: Int) {
        holderPlatei.bind(model.get(position));
    }
}