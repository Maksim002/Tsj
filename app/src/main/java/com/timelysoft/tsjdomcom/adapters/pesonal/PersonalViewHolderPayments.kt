package com.timelysoft.tsjdomcom.adapters.pesonal

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.InvoicesAccounts
import com.timelysoft.tsjdomcom.utils.MyUtils

class PersonalViewHolderPayments(itemView: View,private val listener: PersonalListener) :
    RecyclerView.ViewHolder(itemView) {

    var title = itemView.findViewById<TextView>(R.id.scrip)
    var adres = itemView.findViewById<TextView>(R.id.number)
    var imageIcon = itemView.findViewById<ImageView>(R.id.icon)


    fun bind(item: InvoicesAccounts) {
        title.text = item.amount.toString()
        adres.text = MyUtils.toMyDate(item.date.toString())

        imageIcon.setOnClickListener {
            listener.onClickDownload(item.id)
        }
    }
}