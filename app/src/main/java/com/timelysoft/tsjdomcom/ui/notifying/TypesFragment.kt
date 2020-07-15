package com.timelysoft.tsjdomcom.ui.notifying

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.types.TypesAdapter
import com.timelysoft.tsjdomcom.adapters.types.TypesModel
import kotlinx.android.synthetic.main.fragment_types.*

class TypesFragment : Fragment() {
    private val myAdapter = TypesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_types, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        getTypesType()
        initArgument()
    }

    private fun initArgument() {
        types_add.setOnClickListener {
            findNavController().navigate(R.id.navigation_types_add)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<TypesModel> = arrayListOf()
        list.add(TypesModel(""))
        list.add(TypesModel(""))
        list.add(TypesModel(""))
        list.add(TypesModel(""))
        list.add(TypesModel(""))

        myAdapter.update(list)
        types_recycler.adapter = myAdapter
    }

    private fun getTypesType() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterTypesType = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        types_type_out.setAdapter(adapterTypesType)

        types_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        types_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                types_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                types_type_out.clearFocus()
            }
        types_type_out.setOnClickListener {
            types_type_out.showDropDown()
        }
        types_type_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    types_type_out.showDropDown()
                }
                if (!hasFocus && types_type_out.text!!.isNotEmpty()) {
                    types_type.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    types_type.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        types_type_out.clearFocus()
    }
}