package com.timelysoft.tsjdomcom.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.InvoicesAccounts

class InvoiceAdapter(listener: PersonalListener) :
    RecyclerView.Adapter<ViewHolderPayments>() {

    private var model: List<InvoicesAccounts> = ArrayList()
    private var listene: PersonalListener

    init {
        this.listene = listener
    }


    fun listUpdate(list: List<InvoicesAccounts>) {
        model = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPayments {
        return ViewHolderPayments(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal, parent, false),
            listene
        )
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holderPlatei: ViewHolderPayments, position: Int) {
        holderPlatei.bind(model.get(position));
    }
}