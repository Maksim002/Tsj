package com.example.tsj.adapters.contacts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.common.GenericRecyclerAdapter
import com.example.tsj.common.ViewHolder
import com.example.tsj.service.model.AddressModel
import kotlinx.android.synthetic.main.item_contacts.view.*

class ContactsAdapter(item: List<AddressModel>,private val listener: ContactListener) : GenericRecyclerAdapter<AddressModel>(item) {
    override fun bind(item: AddressModel, holder: ViewHolder) {
        holder.itemView.contacts_title.text = item.address
        holder.itemView.contacts_content.text = item.licNumber.toString()
        holder.itemView.setOnClickListener {
            listener.onClickItem(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_contacts)
    }


}