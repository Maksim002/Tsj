package com.timelysoft.tsjdomcom.ui.balance


import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.AddressModel
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.android.synthetic.main.fragment_balance.view.*

class BalanceFragment : Fragment() {
    private lateinit var viewModel: BalanceViewModel
    private var placementId: Int = 0
    private lateinit var address: String
    private lateinit var listAddress: List<AddressModel>
    private lateinit var addressesAutoComplete: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_balance, container, false)
        viewModel = ViewModelProviders.of(this).get(BalanceViewModel::class.java)
        addressesAutoComplete = root.findViewById(R.id.balance_address)
        root.balance_show_button.setOnClickListener {
            if (validate()) {
                if (placementId != 0) {
                    val bundle = Bundle()
                    bundle.putInt("placementId", placementId)
                    bundle.putString("address", address)
                    findNavController().navigate(R.id.navigation_balance_detail, bundle)
                } else {
                    Toast.makeText(context, "Не выбраны данные", Toast.LENGTH_LONG).show()
                }
            }
        }
        (activity as AppCompatActivity).supportActionBar?.show()
        return root
    }

    private fun validate(): Boolean {
        var valid = true
        if (addressesAutoComplete.text.toString().isEmpty()) {
            balance_address_out.error = "Выберите адрес"
            valid = false
        } else {
            balance_address_out.isErrorEnabled = false
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        initData()
        getAutoOperation()
        initHint()
    }

    private fun initData() {
        MainActivity.alert.show()
        viewModel.addresses().observe(this, Observer { address ->
            listAddress = address
            val addressAdapter =
                ArrayAdapter<AddressModel>(context!!, android.R.layout.simple_dropdown_item_1line, listAddress)
            addressesAutoComplete.setAdapter(addressAdapter)
            MainActivity.alert.hide()
        })
    }

    private fun initHint() {
        if (addressesAutoComplete.text.isNotEmpty())
            balance_address_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }

    private fun getAutoOperation() {

        addressesAutoComplete.keyListener = null
        addressesAutoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                addressesAutoComplete.showDropDown()
                balance_address_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                placementId = listAddress[position].placementId
                address = listAddress[position].address!!
                addressesAutoComplete.clearFocus()
            }
        addressesAutoComplete.setOnClickListener {
            addressesAutoComplete.showDropDown()
        }
        addressesAutoComplete.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            try {
                addressesAutoComplete.showDropDown()

                if (!hasFocus && addressesAutoComplete.text.isNotEmpty()) {
                    balance_address_out.isErrorEnabled = false
                }

            } catch (e: Exception) {
            }
        }
    }
}
