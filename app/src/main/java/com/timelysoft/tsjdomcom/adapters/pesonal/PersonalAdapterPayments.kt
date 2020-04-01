package com.timelysoft.tsjdomcom.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.InvoicesAccounts
import com.timelysoft.tsjdomcom.ui.personal.PersonalListener

class PersonalAdapterPayments(listener: PersonalListener) :
    RecyclerView.Adapter<PersonalViewHolderPayments>() {

    private var model: List<InvoicesAccounts> = ArrayList()
    private var listene: PersonalListener

    init {
        this.listene = listener
    }


    fun listUpdate(list: List<InvoicesAccounts>) {
        model = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolderPayments {
        return PersonalViewHolderPayments(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal, parent, false),
            listene
        )
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holderPlateiPersonal: PersonalViewHolderPayments, position: Int) {
        holderPlateiPersonal.bind(model.get(position));
    }
}