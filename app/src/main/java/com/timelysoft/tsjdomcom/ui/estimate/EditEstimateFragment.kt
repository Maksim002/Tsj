package com.timelysoft.tsjdomcom.ui.estimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateAdapter
import com.timelysoft.tsjdomcom.adapters.edit.EditEstimateModel
import kotlinx.android.synthetic.main.fragment_edit_estimate.*

class EditEstimateFragment : Fragment() {

    private var myAdapter = EditEstimateAdapter()
    private var list: ArrayList<EditEstimateModel> = arrayListOf()

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
            list.add(EditEstimateModel(""))
            edit_estimate_layout_result.visibility = View.VISIBLE
            myAdapter.update(list)
            myAdapter.notifyItemRangeChanged(list.size, list.size)
        }
    }

    private fun initRecyclerView() {
        edit_estimate_recycler.adapter = myAdapter
    }
}