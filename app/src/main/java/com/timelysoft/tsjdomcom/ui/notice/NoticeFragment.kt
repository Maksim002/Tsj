package com.timelysoft.tsjdomcom.ui.notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.notice.NoticeAdapter
import com.timelysoft.tsjdomcom.adapters.notice.NoticeModel
import kotlinx.android.synthetic.main.fragment_notice.*

class NoticeFragment : Fragment() {

    private var myAdapter = NoticeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
    }

    private fun initRecycler() {
        val list: ArrayList<NoticeModel> = arrayListOf()
        list.add(NoticeModel(""))
        list.add(NoticeModel(""))
        list.add(NoticeModel(""))
        list.add(NoticeModel(""))

        myAdapter.update(list)
        notice_recycler.adapter = myAdapter
    }
}