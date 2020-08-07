package com.timelysoft.tsjdomcom.ui.servise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.service.ServiceAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.service.AssociationServicesModel
import com.timelysoft.tsjdomcom.service.model.service.ServiceModel
import kotlinx.android.synthetic.main.fragment_service.*
import kotlinx.android.synthetic.main.fragment_service.view.*

class ServiceFragment : Fragment() {
    private var viewModel = ServiceViewModel()

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
        view.service_recycler.adapter = myAdapter
        viewModel.listService().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    myAdapter.update(data!!.services as ArrayList<ServiceModel>)
                    myAdapter.notifyDataSetChanged()
                }
                Status.ERROR, Status.NETWORK -> {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}