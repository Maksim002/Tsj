package com.example.tsj.ui.balance


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
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.model.AddressModel
import kotlinx.android.synthetic.main.fragment_balance.*
import kotlinx.android.synthetic.main.fragment_balance.view.*
import kotlinx.android.synthetic.main.fragment_history.*

class BalanceFragment : Fragment() {
    private lateinit var viewModel: BalanceViewModel
    private var placementId: Int = 0
    private lateinit var address: String
    private lateinit var model: AddressModel
    private lateinit var textComplete: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_balance, container, false)
        viewModel = ViewModelProviders.of(this).get(BalanceViewModel::class.java)
        textComplete = root.findViewById(R.id.autoCompleteTextView)
        model = AddressModel()
        MainActivity.alert.show()
        root.balance_show_button.setOnClickListener {
            if (validate()){
                if (placementId != 0) {
                    val bundle = Bundle()
                    bundle.putInt("placementId", placementId)
                    bundle.putString("address", address)

                    findNavController().navigate(R.id.navigation_balance_detail, bundle)
                } else {
                    Toast.makeText(context, "Не выбраны данные", Toast.LENGTH_LONG).show()
                }
            }
            (activity as AppCompatActivity).supportActionBar?.show()

            }
        return root
    }

    private fun validate(): Boolean{
        var valid = true
        if (autoCompleteTextView.getText().toString().length == 0) {
            name_text_input.setError("Выберите адрес")
            valid = false
        }else{
            name_text_input.error = null
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        getAutoOperation()
        initHint()
    }

    private fun initHint() {
        if (autoCompleteTextView.text.isNotEmpty())
            name_text_input.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))


    }

    fun getAutoOperation() {
        var listAddress = ArrayList<AddressModel>()
        viewModel.addresses().observe(this, Observer { addres ->
            val list = addres.map {
                it.address
            }
            listAddress = addres as ArrayList<AddressModel>
            val adapterO =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            textComplete.setAdapter(adapterO)
            MainActivity.alert.hide()
        })
        textComplete.setKeyListener(null)
        textComplete.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                textComplete.showDropDown()
                name_text_input.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                placementId = listAddress.get(position).placementId!!
                address = listAddress.get(position).address!!

            }
        textComplete.setOnClickListener {
            textComplete.showDropDown()
        }
        textComplete.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            try {
                textComplete.showDropDown()
                println()
            } catch (e: Exception) {
            }
        }
    }
}
