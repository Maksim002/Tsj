package com.timelysoft.tsjdomcom.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.setting.ScheduleWorksAdapter
import com.timelysoft.tsjdomcom.adapters.setting.ScheduleWorksModel
import kotlinx.android.synthetic.main.fragment_schedule_works.*

class ScheduleWorksFragment : Fragment() {

    private var myAdapter = ScheduleWorksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_works, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        initArgument()
        initRecycler()
    }

    private fun initRecycler() {
        val list: ArrayList<ScheduleWorksModel> = arrayListOf()
        list.add(ScheduleWorksModel(""))
        list.add(ScheduleWorksModel(""))
        list.add(ScheduleWorksModel(""))
        list.add(ScheduleWorksModel(""))

        myAdapter.update(list)
        schedule_works_recycler.adapter = myAdapter
    }

    private fun initArgument() {
        schedule_works_add.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("zero", 0)
            findNavController().navigate(R.id.navigation_schedule_works_add, bundle)
        }
    }
}