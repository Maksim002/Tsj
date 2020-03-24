package com.example.tsj.ui.reference

import android.content.res.ColorStateList
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
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.adapters.references.ReferencesAdapter
import com.example.tsj.adapters.references.ReferencesListener
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.ReferenceLiteModel
import kotlinx.android.synthetic.main.fragment_reference.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHint()
    }

    private fun initHint() {
        if (reference_address.text.isNotEmpty()){
            reference_address_text.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun initData(root: View) {
        MainActivity.alert.show()
        viewModel.addresses().observe(this, Observer {
            val addressAdapter = ArrayAdapter<AddressModel>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            root.reference_address.setAdapter(addressAdapter)
            MainActivity.alert.hide()
        })


    }

    private fun initViews(root: View) {

        root.new_references.setOnClickListener {
            AddUpdateReferenceFragment.list.clear()
            val bundle = Bundle()
            bundle.putInt("placementId", placementId)
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
            placementId = (parent.getItemAtPosition(position) as AddressModel).placementId
            MainActivity.alert.show()
            viewModel.references(placementId).observe(this, Observer {
                adapter.update(it)
                MainActivity.alert.hide()
            })
            root.new_references.visibility = View.VISIBLE
        }

        if (placementId != 0) {
            MainActivity.alert.show()
            viewModel.references(placementId).observe(this, Observer {
                adapter.update(it)
                MainActivity.alert.hide()
            })
            root.new_references.visibility = View.VISIBLE
        }


    }

    override fun onClickItem(item: ReferenceLiteModel) {
        AddUpdateReferenceFragment.list.clear()
        val bundle = Bundle()
        bundle.putInt("referenceId", item.id)
        bundle.putInt("placementId", placementId)
        findNavController().navigate(R.id.navigation_new_reference, bundle)
    }
}
