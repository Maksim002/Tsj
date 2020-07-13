package com.timelysoft.tsjdomcom.ui.provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.ProviderAdapter
import com.timelysoft.tsjdomcom.adapters.provider.ProviderListener
import com.timelysoft.tsjdomcom.adapters.provider.ProviderModel
import kotlinx.android.synthetic.main.fragment_provider.*

class ProviderFragment : Fragment(), ProviderListener {

    private var myAdapter = ProviderAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider, container, false)
    }

    companion object{
        val counterList = ArrayList<ProviderModel>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
        initClick()
    }

    private fun initClick() {
        provider_new_provider.setOnClickListener {
            findNavController().navigate(R.id.navigation_create_supplier)
        }
    }

    private fun initRecyclerView() {
        provider_recycler.adapter = myAdapter
        myAdapter.update(counterList)
    }

    override fun editClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        findNavController().navigate(R.id.navigation_create_supplier, bundle)
    }
}