package com.example.tsj.ui.reference


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.service.model.RelativeModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.alert_for_who.*
import kotlinx.android.synthetic.main.fragment_families.*
import kotlinx.android.synthetic.main.fragment_families.goneL
import kotlinx.android.synthetic.main.fragment_families.view.*
import java.util.*


class RelativeFragment : Fragment() {

    private lateinit var viewModel: ReferenceViewModel
    private var relativeId = 0
    private var relative = ""
    private var position = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_families, container, false)
        initArguments()
        initViews(root)
        initData(root)
        return root
    }

    private fun initArguments() {
        position = try {
            arguments!!.getInt("position")
        } catch (e: java.lang.Exception) {
            -1
        }
    }


    private fun initData(root: View) {
        viewModel.relatives().observe(this, Observer {
            val adapter = ArrayAdapter<MessagesPersonsModel>(
                context!!,
                android.R.layout.simple_dropdown_item_1line,
                it
            )
            root.text_families_who.setAdapter(adapter)
            if (position != -1) {

//                it.forEach { relative ->
//                    if (relative.id == AddUpdateReferenceFragment.list[position].relativeId) {
//                        root.text_families_who.setText(relative.name)
//                    }
//                }
            }

        })
    }


    private fun initViews(root: View) {
        if (position != -1) {
            root.text_families_date.setText(MyUtils.toMyDate(AddUpdateReferenceFragment.list[position].dateOfBirth))
            root.edit_families.setText(AddUpdateReferenceFragment.list[position].fullName)

        }
        root.findViewById<Button>(R.id.buttonFamilies).setOnClickListener {
            if (position == -1) {
                AddUpdateReferenceFragment.list.add(
                    RelativeModel(
                        relativeId,
                        MyUtils.toServerDate(root.text_families_date.text.toString()),
                        root.edit_families.text.toString(),
                        relative
                    )
                )
            } else {
                AddUpdateReferenceFragment.list[position] = RelativeModel(
                    relativeId,
                    MyUtils.toServerDate(root.text_families_date.text.toString()),
                    root.edit_families.text.toString(),
                    relative
                )
            }

            findNavController().popBackStack()

        }

        root.edit_families.setOnFocusChangeListener { _, _ ->
            var col: ColorStateList =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            root.text_families_name.defaultHintTextColor = col

        }

        root.text_families_date.keyListener = null
        root.text_families_date.setOnFocusChangeListener { _, b ->
            if (b) {
                val mDet = DatePickerDialog.OnDateSetListener { _: DatePicker, year, month, day ->
                    val data = MyUtils.convertDate(day, month, year)
                    root.text_families_date.setText(data)
                }
                val calendar = Calendar.getInstance()
                val year: Int = calendar.get(Calendar.YEAR)
                val month: Int = calendar.get(Calendar.MONTH)
                val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
                val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.text_date.defaultHintTextColor = col
                val date = context?.let {
                    DatePickerDialog(
                        it,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDet,
                        year,
                        month,
                        day
                    )
                }
                date?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                date?.show()
                root.goneL.requestFocus()
            }
        }

        root.text_families_who.keyListener = null
        root.text_families_who.onItemClickListener = clickListener(root)

        root.text_families_who.setOnClickListener {
            root.text_families_who.showDropDown()
        }
        root.text_families_who.onFocusChangeListener = View.OnFocusChangeListener { _, b ->
            if (b) {
                try {
                    root.text_families_who.showDropDown()
                } catch (e: Exception) {

                }
            } else {
                println()
            }
        }

    }


    fun clickListener(root: View): AdapterView.OnItemClickListener {
        return AdapterView.OnItemClickListener { parent, _, position, _ ->
            root.text_families_who.showDropDown()
            val col: ColorStateList =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            root.text_families.defaultHintTextColor = col
            relativeId = (parent.getItemAtPosition(position) as MessagesPersonsModel).id
            relative = (parent.getItemAtPosition(position) as MessagesPersonsModel).name
        }
    }


}