package com.timelysoft.tsjdomcom.ui.message.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.message.MassageListAdapter
import com.timelysoft.tsjdomcom.adapters.message.MassageListModel
import kotlinx.android.synthetic.main.fragment_massage_list.*

class MassageListFragment : Fragment(){
    private var myAdapter = MassageListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_massage_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        val list: ArrayList<MassageListModel> = arrayListOf()
        list.add(MassageListModel("effefef"))
        list.add(MassageListModel("effefef"))
        list.add(MassageListModel("effefef"))
        list.add(MassageListModel("effefef"))
        list.add(MassageListModel("effefef"))
        myAdapter.update(list)
        massage_list_recycler.adapter = myAdapter
    }
}
