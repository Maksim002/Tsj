package com.timelysoft.tsjdomcom.ui.requests


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.RequestAddressesModel
import com.timelysoft.tsjdomcom.service.model.RequestTypeModel
import com.timelysoft.tsjdomcom.service.request.AddRequest
import com.timelysoft.tsjdomcom.service.request.UpdateRequest
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_bid_add.*
import kotlinx.android.synthetic.main.fragment_bid_add.view.*
import java.lang.Exception

class RequestAddFragment : Fragment() {

    private lateinit var viewModel: RequestViewModel
    private var requestTypeId = 0
    private var placementId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        val root = inflater.inflate(R.layout.fragment_bid_add, container, false)
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        initViews(root)
        initData()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHint()
    }

    private fun initHint() {
        if (bid_add_flat.text!!.isNotEmpty()) {
            text_bid_add_porch.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            text_bid_add_flat.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            text_bid_add_adres.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            text_request_description.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun validate(): Boolean {
        var valid = true

        if (bid_add_porch.text.toString().isEmpty()) {
            text_bid_add_porch.error = "Поле не должно быть пустым"
            valid = false
        } else {
            text_bid_add_porch.isErrorEnabled = false
        }

        if (bid_add_flat.text.toString().isEmpty()) {
            text_bid_add_flat.error = "Поле не должно быть пустым"
            valid = false
        } else {
            text_bid_add_flat.isErrorEnabled = false
        }

        if (bid_add_adres.text.toString().isEmpty()) {
            text_bid_add_adres.error = "Выберите адрес"
            valid = false
        } else {
            text_bid_add_adres.isErrorEnabled = false
        }

        if (request_description.text.toString().isEmpty()) {
            text_request_description.error = "Поле не должно быть пустым"
            valid = false
        } else {
            text_request_description.isErrorEnabled = false
        }

        try {
            requestTypeId = (bid_add_type.selectedItem as RequestTypeModel).requestTypeId
        } catch (e: Exception) {
            bid_add_type.error = "Заполните поле"
            valid = false
        }
        return valid
    }

    private fun initViews(root: View) {

        root.request_add.setOnClickListener {
            if (validate()) {
                MainActivity.alert.show()
                if (RequestDetailFragment.requestModel.id != null) {
                    val body = UpdateRequest()
                    body.id = RequestDetailFragment.requestModel.id
                    body.description = root.request_description.text.toString()
                    body.floor = root.bid_add_flat.text.toString().toInt()
                    body.entrance = root.bid_add_porch.text.toString().toInt()
                    body.requestTypeId = requestTypeId

                    viewModel.updateRequest(body).observe(this, Observer {
                        if (it) {
                            Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(context, "ошибка", Toast.LENGTH_LONG).show()
                        }
                        MainActivity.alert.hide()
                    })
                } else {
                    val body = AddRequest(
                        placementId,
                        requestTypeId,
                        root.bid_add_porch.text.toString().toInt(),
                        root.bid_add_flat.text.toString().toInt(),
                        root.request_description.text.toString()
                    )

                    viewModel.addRequest(body).observe(this, Observer {
                        if (it) {
                            Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(context, "ошибка", Toast.LENGTH_LONG).show()
                        }
                        MainActivity.alert.hide()
                    })
                }
            }
        }

        //types

        //addresses
        root.bid_add_adres.keyListener = null

        root.bid_add_adres.setOnClickListener {
            root.bid_add_adres.showDropDown()
        }


        root.bid_add_flat.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.bid_add_flat.text!!.isNotEmpty()) {
                text_bid_add_flat.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.text_bid_add_flat.isErrorEnabled = false
            }
        }
        root.bid_add_porch.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.bid_add_porch.text!!.isNotEmpty()) {
                text_bid_add_porch.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.text_bid_add_porch.isErrorEnabled = false
            }
        }
        root.request_description.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.request_description.text!!.isNotEmpty()) {
                text_request_description.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.text_request_description.isErrorEnabled = false
            }
        }

        root.bid_add_adres.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            MyUtils.hideKeyboard(activity!!, view!!)
            root.bid_add_adres.showDropDown()
            if (!hasFocus && bid_add_adres.text.isNotEmpty()) {
                root.text_bid_add_adres.isErrorEnabled = false
            }
        }

        root.bid_add_adres.setOnItemClickListener { parent, view, position, id ->
            placementId = (parent.getItemAtPosition(position) as RequestAddressesModel).placementId
            text_bid_add_adres.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            root.bid_add_adres.clearFocus()
        }

        if (RequestDetailFragment.requestModel.id != null) {
            root.bid_add_porch.setText(RequestDetailFragment.requestModel.entrance.toString())
            root.bid_add_flat.setText(RequestDetailFragment.requestModel.floor.toString())
            root.request_description.setText(RequestDetailFragment.requestModel.description.toString())
            root.bid_add_adres.setText(RequestDetailFragment.requestModel.address.toString())
            root.bid_add_adres.isClickable = false
            root.bid_add_adres.isEnabled = false
            root.request_add.text = "Обновить"
            try {
                (activity as AppCompatActivity?)!!.supportActionBar?.title = "Обновить"
            } catch (e: Exception) {
            }

        }
    }

    private fun initData() {
        MainActivity.alert.show()
        viewModel.requestTypes().observe(this, Observer {
            //types
            val typeAdapter = ArrayAdapter<RequestTypeModel>(
                context!!,
                R.layout.item_spinner_adapter,
                it
            )
            typeAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
            bid_add_type.adapter = typeAdapter
            if (RequestDetailFragment.requestModel.id != null) {
                it.forEachIndexed { index, model ->
                    if (RequestDetailFragment.requestModel.requestTypeName == model.requestTypeName){
                        bid_add_type.setSelection(index+1)
                    }
                }
            }
            MainActivity.alert.hide()

        })

        viewModel.requestAddresses().observe(this, Observer {
            //адреса
            val typeAdapter = ArrayAdapter<RequestAddressesModel>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            bid_add_adres.setAdapter(typeAdapter)
            MainActivity.alert.hide()
        })
    }
}
