package com.example.tsj.adapters.pesonal

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.model.PersonalModel

class PersonalViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    var title = itemView.findViewById<TextView>(R.id.scrip)
    var adres = itemView.findViewById<TextView>(R.id.number)

    fun bind(get: PersonalModel) {
        title.setText(get.title)
        adres.setText(get.adres)
    }

}