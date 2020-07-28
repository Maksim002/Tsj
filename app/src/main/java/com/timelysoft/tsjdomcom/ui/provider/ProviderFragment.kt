package com.timelysoft.tsjdomcom.ui.provider

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
import com.timelysoft.tsjdomcom.adapters.provider.ProviderAdapter
import com.timelysoft.tsjdomcom.adapters.provider.ProviderListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.ProviderModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_provider.*

class ProviderFragment : Fragment(), ProviderListener {

    private var myAdapter = ProviderAdapter(this)

    private var viewModel = ProviderViewModel()

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
            findNavController().navigate(R.id.navigation_create_supplier)
        }
    }

    private fun initRecyclerView() {
        MainActivity.alert.show()
        provider_recycler.adapter = myAdapter
        viewModel.provider().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    myAdapter.update(data!!)
                    myAdapter.notifyDataSetChanged()
                    MainActivity.alert.hide()
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun editClick(position: Int, item: ProviderModel) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putSerializable("provider", item)
        findNavController().navigate(R.id.navigation_create_supplier, bundle)
    }

    override fun clearProviderClick(item: ProviderModel) {
        viewModel.providerDelete(item.id).observe(this, Observer {result ->
            val msg = result.msg
            when(result.status){
                Status.SUCCESS ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
                Status.NETWORK, Status.ERROR ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}