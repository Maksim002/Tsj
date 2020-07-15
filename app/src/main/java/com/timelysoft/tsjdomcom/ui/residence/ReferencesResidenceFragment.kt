package com.timelysoft.tsjdomcom.ui.residence

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.residence.ReferencesResidenceAdapter
import com.timelysoft.tsjdomcom.adapters.residence.ReferencesResidenceModel
import kotlinx.android.synthetic.main.fragment_references_residence.*

class ReferencesResidenceFragment : Fragment() {

    private val myAdapter = ReferencesResidenceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_references_residence, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        references_residence_add_help.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_help)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<ReferencesResidenceModel> = arrayListOf()
        list.add(ReferencesResidenceModel(""))
        list.add(ReferencesResidenceModel(""))
        list.add(ReferencesResidenceModel(""))
        list.add(ReferencesResidenceModel(""))

        myAdapter.update(list)
        references_residence_recycler.adapter = myAdapter
    }
}