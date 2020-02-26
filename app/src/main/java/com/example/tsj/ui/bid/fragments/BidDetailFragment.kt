package com.example.tsj.ui.bid.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R

class BidDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        arguments?.getInt("id")
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.fragment_bid_detail, container, false)

    }


}
