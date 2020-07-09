package com.timelysoft.tsjdomcom.ui.estimate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateAdapter
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateListener
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateModel
import com.timelysoft.tsjdomcom.adapters.edit.EstimateComponentModel
import kotlinx.android.synthetic.main.fragment_edit_estimate.*
import kotlin.math.min

class EditEstimateFragment : Fragment(), EditEstimateListener {
    private val list: ArrayList<EditEstimateModel> = arrayListOf()

    private var myAdapter = EditEstimateAdapter(this)
    private var item: ArrayList<EstimateComponentModel> = arrayListOf()


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
        initArgument()
    }

    private fun initArgument() {
        edit_estimate_add_item.setOnClickListener {
            list.add(EditEstimateModel(" ", item))
            myAdapter.update(list)
        }
    }

    private fun initRecyclerView() {
        edit_estimate_recycler.adapter = myAdapter
    }

    override fun editEstimateClickListener(item: EditEstimateModel, position: Int) {
        list.removeAt(position )
    }
}