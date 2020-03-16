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
import com.example.tsj.adapters.references.ReferencesAdapter
import com.example.tsj.adapters.references.ReferencesListener
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.ReferenceLiteModel
import kotlinx.android.synthetic.main.fragment_reference.view.*

class ReferenceFragment : Fragment(),ReferencesListener {

    private lateinit var viewModel: ReferenceViewModel
    private lateinit var adapter: ReferencesAdapter
    private var placementId = 0
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

        root.new_references.setOnClickListener {
            AddReferenceFragment.list.clear()
            val bundle = Bundle()
            bundle.putInt("id", placementId)
            findNavController().navigate(R.id.navigation_new_reference, bundle)
        }
        adapter = ReferencesAdapter(ArrayList(),this)
        root.recyclerViewReferences.adapter = adapter


        root.reference_address.keyListener = null
        root.reference_address.setOnClickListener {
            root.reference_address.showDropDown()
        }
        root.reference_address.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                root.reference_address.showDropDown()
            }
        }
        root.reference_address.setOnItemClickListener { parent, _, position, _ ->
            placementId = (parent.getItemAtPosition(position) as AddressModel).placementId!!
            viewModel.references(placementId).observe(this, Observer {
                adapter.update(it)
            })
            root.new_references.visibility = View.VISIBLE
        }

        if (placementId != 0) {
            viewModel.references(placementId).observe(this, Observer {
                adapter.update(it)
            })
            root.new_references.visibility = View.VISIBLE
        }


    }

    override fun onClickItem(item: ReferenceLiteModel) {
        AddReferenceFragment.list.clear()
        val bundle = Bundle()
        bundle.putInt("referenceId", item.id)
        bundle.putInt("id", placementId)
        bundle.putBoolean("update", true)
        findNavController().navigate(R.id.navigation_new_reference, bundle)
    }

}
