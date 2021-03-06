package com.timelysoft.tsjdomcom.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.setting.StaffAdapter
import com.timelysoft.tsjdomcom.adapters.setting.StaffModel
import kotlinx.android.synthetic.main.fragment_staff.*

class StaffFragment : Fragment() {
    private var myAdapter = StaffAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArgument()
        initRecycler()
    }

    private fun initRecycler() {
        val list: ArrayList<StaffModel> = arrayListOf()
        list.add(StaffModel(""))
        list.add(StaffModel(""))
        list.add(StaffModel(""))
        list.add(StaffModel(""))

        myAdapter.update(list)
        staff_recycler.adapter = myAdapter
    }

    private fun initArgument() {
        staff_add.setOnClickListener { 
            val bundle = Bundle()
            bundle.putInt("zero", 0)
            findNavController().navigate(R.id.navigation_staff_add, bundle)
        }
    }
}