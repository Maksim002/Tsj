package com.timelysoft.tsjdomcom.ui.servise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.service.ServiceAdapter
import com.timelysoft.tsjdomcom.adapters.service.ServiceModel
import kotlinx.android.synthetic.main.fragment_service.*
import kotlinx.android.synthetic.main.fragment_service.view.*

class ServiceFragment : Fragment() {

    private var myAdapter = ServiceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler(view)
        initRegistration()
    }

    private fun initRegistration() {
        service_registration_service.setOnClickListener {
            findNavController().navigate(R.id.navigation_registration_service)
        }

        service_create_type.setOnClickListener {
            findNavController().navigate(R.id.navigation_create_service)
        }
    }

    private fun initRecycler(view: View) {

        val list: ArrayList<ServiceModel> = arrayListOf()
        list.add(ServiceModel(""))
        list.add(ServiceModel(""))
        list.add(ServiceModel(""))
        list.add(ServiceModel(""))
        list.add(ServiceModel(""))

        myAdapter.update(list)
            view.service_recycler.adapter = myAdapter
    }
}