package com.timelysoft.tsjdomcom.ui.servise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.service.RegistrationServiceAdapter
import com.timelysoft.tsjdomcom.adapters.service.RegistrationServiceModel
import kotlinx.android.synthetic.main.fragment_registration_service.*

class  RegistrationServiceFragment : Fragment() {
    private var myAdapter =  RegistrationServiceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        initRegistration()
    }

    private fun initRegistration() {
        service_registration_service.setOnClickListener {
            findNavController().navigate(R.id.navigation_towards_house)
        }
        service_create_type.setOnClickListener {
            findNavController().navigate(R.id.navigation_service_apartment)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<RegistrationServiceModel> = arrayListOf()
        list.add(RegistrationServiceModel(""))
        list.add(RegistrationServiceModel(""))
        list.add(RegistrationServiceModel(""))
        list.add(RegistrationServiceModel(""))
        list.add(RegistrationServiceModel(""))

        myAdapter.update(list)
        registration_service_recycler.adapter = myAdapter
    }
}