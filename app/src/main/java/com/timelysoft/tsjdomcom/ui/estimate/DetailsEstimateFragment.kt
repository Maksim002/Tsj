package com.timelysoft.tsjdomcom.ui.estimate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.estimate.EstimateModel
import kotlinx.android.synthetic.main.fragment_detail_estimate.*
import java.lang.Exception

class DetailsEstimateFragment : Fragment() {
    private lateinit var item: EstimateModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_estimate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getBundle()
        initArgemtnt()
    }

    private fun initArgemtnt() {
        detail_estimate.setText(item.addredd)
    }

    private fun getBundle () {
        item = try {
            arguments!!.getSerializable("list")
        }catch (e: Exception){
            ""
        } as EstimateModel
    }
}