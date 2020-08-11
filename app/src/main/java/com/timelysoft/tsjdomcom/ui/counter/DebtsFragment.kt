package com.timelysoft.tsjdomcom.ui.counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.debts.DebtsAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.counter.DebtModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_debts.*

class DebtsFragment : Fragment() {
    private var viewModel = CounterViewModel()

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
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        MainActivity.alert.show()
        debts_recycler.adapter = myAdapter
        viewModel.counterListDebts().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    myAdapter.update(data!!.debts as ArrayList<DebtModel>)
                    myAdapter.notifyDataSetChanged()
                    MainActivity.alert.hide()
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}