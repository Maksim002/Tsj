package com.example.tsj.adapters.pesonal

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.service.model.InvoicesAccounts
import com.example.tsj.ui.personal.PersonalListener
import com.example.tsj.utils.MyUtils

class PersonalViewHolderPlatei(itemView: View, listener: PersonalListener) :
    RecyclerView.ViewHolder(itemView) {

    var title = itemView.findViewById<TextView>(R.id.scrip)
    var adres = itemView.findViewById<TextView>(R.id.number)
    var imageIcon = itemView.findViewById<ImageView>(R.id.icon)
    var listener: PersonalListener

    init {
        this.listener= listener
    }


    fun bind(get: InvoicesAccounts) {
        title.setText(get.amount.toString())
        adres.setText(MyUtils.toMyDate(get.date.toString()))

        imageIcon.setOnClickListener {
            listener.onClickDownload(get.id)
        }
    }
}