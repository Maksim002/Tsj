package com.timelysoft.tsjdomcom.ui.contact.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.contacts.ContactListener
import com.timelysoft.tsjdomcom.adapters.contacts.ContactsAdapter
import com.timelysoft.tsjdomcom.service.model.AddressModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_profile_bottom_sheet.view.*

class AccountsBottomSheet(private val accountsListener: AccountsListener, private val item: ArrayList<AddressModel> = ArrayList()) :
    BottomSheetDialogFragment(), ContactListener {
    private lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile_bottom_sheet, container, false)
        initRec(root)
        return root
    }

    private fun initRec(root: View) {
        adapter = ContactsAdapter(item, this)
        root.contacts_recyclerview.adapter = adapter
    }

    override fun onClickItem(addressModel: AddressModel) {
        accountsListener.getLicNumber(addressModel)
    }

    override fun copyLicNumber(text: String) {
        MyUtils.copyText(text, context!!)
        Toast.makeText(context, "Лицевой счёт скопирован!", Toast.LENGTH_SHORT).show()
    }
}
