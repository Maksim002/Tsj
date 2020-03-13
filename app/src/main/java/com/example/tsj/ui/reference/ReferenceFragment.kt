package com.example.tsj.ui.reference


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.references.AdapterReferences
import com.example.tsj.service.model.AddressModel
import kotlinx.android.synthetic.main.fragment_reference.view.*

class ReferenceFragment : Fragment() {

    private lateinit var viewModel: ReferenceViewModel
    private lateinit var adapter: AdapterReferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reference, container, false)
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)
        initViews(root)
        initData(root)
        (activity as AppCompatActivity).supportActionBar?.show()
        return root
    }

    private fun initData(root: View) {
        viewModel.addresses().observe(this, Observer {
            val addressAdapter = ArrayAdapter<AddressModel>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            root.reference_address.setAdapter(addressAdapter)

        })




    }

    private fun initViews(root: View) {

        adapter = AdapterReferences(ArrayList())
        root.recyclerViewReferences.adapter = adapter
        root.setOnClickListener {
            findNavController().navigate(R.id.navigation_new_reference)
        }

        root.reference_address.keyListener = null
        root.reference_address.setOnClickListener {
            root.reference_address.showDropDown()
        }
        root.reference_address.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                root.reference_address.showDropDown()
            }
        }
        root.reference_address.setOnItemClickListener { parent, view, position, id ->
            val placementId = (parent.getItemAtPosition(position) as AddressModel).placementId!!
            viewModel.getReferences(placementId).observe(this, Observer {
                    adapter.update(it)
            })
        }
    }

}
