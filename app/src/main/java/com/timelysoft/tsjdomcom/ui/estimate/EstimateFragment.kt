package com.timelysoft.tsjdomcom.ui.estimate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.estimate.EstimateAdapter
import com.timelysoft.tsjdomcom.adapters.estimate.EstimateClickListener
import com.timelysoft.tsjdomcom.adapters.estimate.EstimateModel
import kotlinx.android.synthetic.main.fragment_estimate.*

class EstimateFragment : Fragment(), EstimateClickListener {
    private var myAdapter = EstimateAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estimate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list:  ArrayList<EstimateModel> = arrayListOf()
        list.add(EstimateModel("dfdfd"))

        myAdapter.update(list)
        estimate_recycler.adapter = myAdapter
    }

    override fun estimateClickListener(item: EstimateModel) {
        val  binder = Bundle()
        binder.putSerializable("list", item)
        findNavController().navigate(R.id.navigation_detail_estimate, binder)
    }
}