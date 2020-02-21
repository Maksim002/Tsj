package com.example.tsj.ui.balance


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tsj.R
import com.example.tsj.adapters.balance.BalanceAdapter
import com.example.tsj.model.BalanceModel
import kotlinx.android.synthetic.main.fragment_balance_detail.*

class BalanceDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_balance_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val items = arrayListOf(

            BalanceModel("Техобслуживание", 145F),
            BalanceModel("Лифт", 152.4F),
            BalanceModel("Техобслуживание", 145F),
            BalanceModel("Лифт", 152.4F),
            BalanceModel("Техобслуживание", 145F),
            BalanceModel("Лифт", 152.4F)
        )

        val adapter = BalanceAdapter(items)
        balance_summ_recyclerview.adapter = adapter

    }


}
