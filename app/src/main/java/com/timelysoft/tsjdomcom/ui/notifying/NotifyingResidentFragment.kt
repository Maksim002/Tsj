package com.timelysoft.tsjdomcom.ui.notifying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.notifying.NotifyingResidentAdapter
import com.timelysoft.tsjdomcom.adapters.notifying.NotifyingResidentClickListener
import com.timelysoft.tsjdomcom.adapters.notifying.NotifyingResidentModel
import kotlinx.android.synthetic.main.fragment_notifying_resident.*

class NotifyingResidentFragment : Fragment(), NotifyingResidentClickListener {

    private val myAdapter = NotifyingResidentAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifying_resident, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        notifying_resident_notification.setOnClickListener {
            findNavController().navigate(R.id.navigation_type)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<NotifyingResidentModel> = arrayListOf()
        list.add(NotifyingResidentModel("Задолженность по услуге 'Членские взносы'"))
        list.add(NotifyingResidentModel("Задолженность по услуге 'Членские взносы'"))
        list.add(NotifyingResidentModel("Задолженность по услуге 'Членские взносы'"))
        list.add(NotifyingResidentModel("Задолженность по услуге 'Членские взносы'"))

        myAdapter.update(list)
        notifying_resident_recycler.adapter = myAdapter
    }

    override fun notifyingResidentClickListener(item: NotifyingResidentModel) {
        val bundle = Bundle()
        bundle.putSerializable("notifying", item)
        findNavController().navigate(R.id.navigation_notification, bundle)
    }
}