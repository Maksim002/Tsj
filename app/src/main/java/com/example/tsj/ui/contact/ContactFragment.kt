package com.example.tsj.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
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

        val ref: LinearLayout = root.findViewById(R.id.reference)

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

        (activity as AppCompatActivity).supportActionBar?.hide()
        return root
    }
}