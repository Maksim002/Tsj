package com.example.tsj.ui.balance


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import kotlinx.android.synthetic.main.fragment_balance.*

class BalanceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_balance, container, false)


        val showBalanceButton : Button = root.findViewById(R.id.balance_show_button)
        // Inflate the layout for this fragment
        showBalanceButton.setOnClickListener {
          findNavController().navigate(R.id.navigation_balance_detail)
        }
        (activity as AppCompatActivity).supportActionBar?.show()

        return root
    }
}
