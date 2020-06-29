package com.timelysoft.tsjdomcom.ui.providers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.ProviderAdapter
import com.timelysoft.tsjdomcom.adapters.provider.ProviderModel
import com.timelysoft.tsjdomcom.adapters.user.UserModel
import kotlinx.android.synthetic.main.fragment_provider.*
import java.lang.Exception

class ProviderFragment : Fragment() {

    private var myAdapter = ProviderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
        initClick()
    }

    private fun initClick() {
        provider_new_provider.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("false", false)
            findNavController().navigate(R.id.navigation_create_supplier, bundle)
        }
    }

    private fun initRecyclerView() {

        val list: ArrayList<ProviderModel> = arrayListOf()
        list.add(ProviderModel("ОсОО “Чистый Дом”", "", "", "", "", "false", "", ""))

        myAdapter.update(list)
        provider_recycler.adapter = myAdapter
    }
}