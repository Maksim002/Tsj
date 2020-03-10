package com.example.tsj.ui.message.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import kotlinx.android.synthetic.main.alert_for_who.*

/**
 * A simple [Fragment] subclass.
 */
class ForWhoFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.alert_for_who, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        first_alert.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_message)
        }

        second_alert.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_message)
        }

    }


}
