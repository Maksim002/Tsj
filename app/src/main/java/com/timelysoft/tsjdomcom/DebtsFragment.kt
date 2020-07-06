package com.timelysoft.tsjdomcom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.adapters.debts.DebtsAdapter
import com.timelysoft.tsjdomcom.adapters.debts.DebtsModel
import kotlinx.android.synthetic.main.fragment_debts.*

class DebtsFragment : Fragment() {

    private var myAdapter = DebtsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<DebtsModel> = arrayListOf()
        list.add(DebtsModel(""))
        list.add(DebtsModel(""))
        list.add(DebtsModel(""))
        list.add(DebtsModel(""))
        list.add(DebtsModel(""))

        myAdapter.update(list)
        debts_recycler.adapter = myAdapter
    }
}