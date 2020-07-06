package com.timelysoft.tsjdomcom.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.unpaid.UnpaidAdapter
import com.timelysoft.tsjdomcom.adapters.unpaid.UnpaidModel
import kotlinx.android.synthetic.main.fragment_unpaid.*

class UnpaidFragment : Fragment() {

    private val myAdapter = UnpaidAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unpaid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<UnpaidModel> = arrayListOf()
        list.add(UnpaidModel(""))
        list.add(UnpaidModel(""))
        list.add(UnpaidModel(""))
        list.add(UnpaidModel(""))
        list.add(UnpaidModel(""))

        myAdapter.update(list)
        unpaid_recycler.adapter = myAdapter
    }
}