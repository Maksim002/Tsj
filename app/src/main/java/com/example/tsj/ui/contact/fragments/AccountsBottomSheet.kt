package com.example.tsj.ui.contact.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R
import com.example.tsj.adapters.contacts.ContactsAdapter
import com.example.tsj.model.ContactsBottomModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_profile_bottom_sheet.*

/**
 * A simple [Fragment] subclass.
 */
class AccountsBottomSheet : BottomSheetDialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.show()
        return inflater.inflate(R.layout.fragment_profile_bottom_sheet, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRec()
    }

    private fun initRec() {
       val item = listOf(
            ContactsBottomModel("2412521","7 небо, Токомбаева, д.53/2 кв 11"),
            ContactsBottomModel("2412521","7 небо, Токомбаева, д.53/2 кв 11")
        )

        val adapter = ContactsAdapter(item)

        contacts_recyclerview.adapter =  adapter

    }


}
