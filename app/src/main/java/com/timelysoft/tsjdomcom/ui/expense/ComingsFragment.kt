package com.timelysoft.tsjdomcom.ui.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.expense.ComingsAdapter
import com.timelysoft.tsjdomcom.adapters.expense.ComingsClickListener
import com.timelysoft.tsjdomcom.adapters.expense.ComingsModel
import kotlinx.android.synthetic.main.fragment_comings.*

class ComingsFragment : Fragment(), ComingsClickListener {

    private var myAdapter = ComingsAdapter(this)
    private val list: ArrayList<ComingsModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comings, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        list.add(ComingsModel("Да"))
        list.add(ComingsModel("нет"))
        list.add(ComingsModel("оно"))
        list.add(ComingsModel("они"))

        myAdapter.update(list)
        comings_recycler.adapter = myAdapter
    }

    override fun comingsOnClickListener(item: ComingsModel) {
        val bundle = Bundle()
        bundle.putSerializable("comings", list)
        findNavController().navigate(R.id.navigation_change, bundle)
    }
}