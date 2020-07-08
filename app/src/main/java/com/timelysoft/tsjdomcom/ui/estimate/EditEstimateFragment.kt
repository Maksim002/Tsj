package com.timelysoft.tsjdomcom.ui.estimate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateAdapter
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateModel
import com.timelysoft.tsjdomcom.adapters.edit.EstimateComponentModel
import kotlinx.android.synthetic.main.fragment_edit_estimate.*

class EditEstimateFragment : Fragment() {

    private var myAdapter = EditEstimateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_estimate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val item: ArrayList<EstimateComponentModel> = arrayListOf()
        val list: ArrayList<EditEstimateModel> = arrayListOf()
        list.add(EditEstimateModel("", item))
        list.add(EditEstimateModel("", item))

        myAdapter.update(list)
        edit_estimate_recycler.adapter = myAdapter
    }
}