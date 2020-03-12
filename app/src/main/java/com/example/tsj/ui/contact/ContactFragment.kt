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
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import com.example.tsj.ui.contact.fragments.AccountsBottomSheet
import com.example.tsj.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_contacts.view.*

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contacts, container, false)

        val imageHistory: ImageView = root.findViewById(R.id.imageHistory)
        val imageBalance: ImageView = root.findViewById(R.id.imageBalance)
        val imageBid: ImageView = root.findViewById(R.id.imageBid)
        val buttonC: Button = root.findViewById(R.id.button_contacts)
        val ref: LinearLayout = root.findViewById(R.id.reference)
        val layoutC: LinearLayout = root.findViewById(R.id.linearLayout)
        val voting : LinearLayout = root.findViewById(R.id.voting)
        val bottomSheet = AccountsBottomSheet()
        buttonC.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java).putExtra("transition", true)
            startActivity(intent)
        }

        if (AppPreferences.isLogined) {
            layoutC.visibility = View.VISIBLE
        } else {
            buttonC.visibility = View.VISIBLE
        }


        if (AppPreferences.isLogined) {
            ref.visibility = View.VISIBLE
        } else {
            ref.visibility = View.GONE
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
            findNavController().navigate(R.id.navigation_profile)
        }


        root.profile.setOnClickListener {
            bottomSheet.show(fragmentManager!!, "AccountsBottomSheet")
        }

        (activity as AppCompatActivity).supportActionBar?.hide()
        return root
    }


}