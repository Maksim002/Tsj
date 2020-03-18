package com.example.tsj.ui.contact.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tsj.R
import com.example.tsj.adapters.contacts.ContactListener
import com.example.tsj.adapters.contacts.ContactsAdapter
import com.example.tsj.service.model.AddressModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_profile_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_profile_bottom_sheet.view.*

class AccountsBottomSheet(
    private val accountsListener: AccountsListener,
    private val item: List<AddressModel> = ArrayList()
) :
    BottomSheetDialogFragment(), ContactListener {
    private lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_bottom_sheet, container, false)
        initRec(root)
        return root
    }


    private fun initRec(root: View) {
        adapter = ContactsAdapter(item,this)
        root.contacts_recyclerview.adapter = adapter
    }

    override fun onClickItem(addressModel: AddressModel) {
        accountsListener.getLicNumber(addressModel)
    }


}
