package com.example.tsj.adapters.contacts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.model.ContactsBottomModel
import kotlinx.android.synthetic.main.item_contacts.view.*

class ContactsAdapter(item : List <ContactsBottomModel>) : GenericRecyclerAdapter <ContactsBottomModel>(item) {
    override fun bind(item: ContactsBottomModel, holder: ViewHolder) {
        holder.itemView.contacts_title.text = item.title
        holder.itemView.contacts_content.text = item.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_contacts)
    }
}