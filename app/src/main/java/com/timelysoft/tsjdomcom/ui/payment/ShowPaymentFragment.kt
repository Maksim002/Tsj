package com.timelysoft.tsjdomcom.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.show.ShowPaymentAdapter
import com.timelysoft.tsjdomcom.adapters.show.ShowPaymentModel
import kotlinx.android.synthetic.main.fragment_show_payment.*

class ShowPaymentFragment : Fragment() {

    private var myAdapter = ShowPaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
        initArguments()
    }

    private fun initArguments() {
        show_payment_history.setOnClickListener {
            findNavController().navigate(R.id.navigation_payment_history)
        }
    }

    private fun initRecyclerView() {
        val list: ArrayList<ShowPaymentModel> = arrayListOf()
        list.add(ShowPaymentModel(""))
        list.add(ShowPaymentModel(""))
        list.add(ShowPaymentModel(""))

        myAdapter.update(list)
        show_payment_recycler.adapter = myAdapter
    }
}