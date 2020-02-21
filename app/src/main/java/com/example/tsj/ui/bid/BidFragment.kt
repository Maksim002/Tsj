package com.example.tsj.ui.bid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tsj.R
import com.example.tsj.adapters.bid.BidAdapter
import com.example.tsj.model.BidModel
import kotlinx.android.synthetic.main.fragment_bid.*

class BidFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val items = arrayListOf(

            BidModel( "18.02.2020 16:16","Заявка на ремонт", "Тестовая заявка на ремонт", true),
            BidModel("18.02.2020 16:16","Жалоба", "Тестовая заявка на ремонт", true),BidModel("18.02.2020 16:16","Заявка на ремонт", "Тестовая заявка на ремонт", true),
            BidModel("18.02.2020 16:16","Жалоба", "Тестовая заявка на ремонт", false),BidModel("18.02.2020 16:16","Заявка на ремонт", "Тестовая заявка на ремонт", true),
            BidModel("18.02.2020 16:16","Жалоба", "Тестовая заявка на ремонт", true),BidModel("18.02.2020 16:16","Заявка на ремонт", "Тестовая заявка на ремонт", true),
            BidModel("18.02.2020 16:16","Жалоба", "Тестовая заявка на ремонт", true),BidModel("18.02.2020 16:16","Заявка на ремонт", "Тестовая заявка на ремонт", true),
            BidModel("18.02.2020 16:16","Жалоба", "Тестовая заявка на ремонт", true),BidModel("18.02.2020 16:16","Заявка на ремонт", "Тестовая заявка на ремонт", true),
            BidModel("18.02.2020 16:16","Жалоба", "Тестовая заявка на ремонт", false)
        )

        val adapter = BidAdapter (items)

        if (adapter.itemCount != 0){
            bid_is_empty_textview.visibility = View.GONE
            bid_recyclerview.visibility = View.VISIBLE
        }else{
            bid_is_empty_textview.visibility = View.VISIBLE
            bid_recyclerview.visibility = View.GONE
        }
        bid_recyclerview!!.adapter = adapter

    }

}
