package com.example.tsj.ui.contact

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import com.example.tsj.service.model.AddressModel
import com.example.tsj.ui.contact.fragments.AccountsBottomSheet
import com.example.tsj.ui.contact.fragments.AccountsListener
import com.example.tsj.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_contacts.view.*

class ContactFragment : Fragment(), AccountsListener {
    private lateinit var viewModel: ContactViewModel
    private lateinit var bottomSheet: AccountsBottomSheet
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contacts, container, false)
        viewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        val imageHistory: ImageView = root.findViewById(R.id.imageHistory)
        val imageBalance: ImageView = root.findViewById(R.id.imageBalance)
        val imageBid: ImageView = root.findViewById(R.id.imageBid)
        val buttonC: Button = root.findViewById(R.id.button_contacts)
        val ref: LinearLayout = root.findViewById(R.id.reference)
        val layoutC: LinearLayout = root.findViewById(R.id.linearLayout)
        bottomSheet = AccountsBottomSheet(this)
        buttonC.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java).putExtra("transition", true)
            startActivity(intent)
        }

        if (AppPreferences.isLogined) {
            layoutC.visibility = View.VISIBLE
            ref.visibility = View.VISIBLE
            root.profile.visibility = View.VISIBLE
            root.voting.visibility = View.VISIBLE
        } else {
            buttonC.visibility = View.VISIBLE
            ref.visibility = View.GONE
            root.profile.visibility = View.GONE
            root.voting.visibility = View.GONE
        }

        ref.setOnClickListener {
            findNavController().navigate(R.id.navigation_reference)
        }

        imageHistory.setOnClickListener {
            findNavController().navigate(R.id.navigation_history)
        }
        imageBalance.setOnClickListener {
            findNavController().navigate(R.id.navigation_balance)
        }
        imageBid.setOnClickListener {
            findNavController().navigate(R.id.navigation_bid)
        }

        root.contacts.setOnClickListener {
            findNavController().navigate(R.id.navigation_feedback)
        }

        root.linearPortalTSJ.setOnClickListener {
            findNavController().navigate(R.id.navigation_portalTSJ)
        }

        root.btn_profile.setOnClickListener {
            if (AppPreferences.isLogined) {
                findNavController().navigate(R.id.navigation_profile)
            } else {
                val intent = Intent(context, LoginActivity::class.java).putExtra("transition", true)
                startActivity(intent)
            }
        }
        viewModel.addresses().observe(this, Observer {
            bottomSheet = AccountsBottomSheet(this, it)
            try {
                contacts_adres.text = it[0].address
                contacts_test.text = it[0].licNumber.toString()
            } catch (e: Exception) {
            }
        })
        root.profile.setOnClickListener {
            bottomSheet.show(fragmentManager!!, "AccountsBottomSheet")
        }

        (activity as AppCompatActivity).supportActionBar?.hide()
        return root
    }

    override fun getLicNumber(addressModel: AddressModel) {
        contacts_adres.text = addressModel.address
        contacts_test.text = addressModel.licNumber.toString()
        bottomSheet.dismiss()
    }
}