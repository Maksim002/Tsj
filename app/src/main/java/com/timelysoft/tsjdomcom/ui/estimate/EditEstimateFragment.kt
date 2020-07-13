package com.timelysoft.tsjdomcom.ui.estimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateTemplatesAdapter
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateTemplatesModel
import kotlinx.android.synthetic.main.fragment_edit_estimate.*
import kotlinx.android.synthetic.main.fragment_edit_estimate.edit_estimate_recycler

class EditEstimateFragment : Fragment() {

    private var myAdapter = EditEstimateTemplatesAdapter()
    private var list: ArrayList<EditEstimateTemplatesModel> = arrayListOf()

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
        initArgument()
        initRecyclerView()
    }

    private fun initArgument() {
        edit_estimate_add_item.setOnClickListener {
            list.add(EditEstimateTemplatesModel("", ""))
            myAdapter.update(list)
            myAdapter.notifyItemRangeChanged(list.size, list.size)
        }
    }


    private fun initRecyclerView() {
        edit_estimate_recycler.adapter = myAdapter
    }
}