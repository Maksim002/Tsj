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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.service.request.RelativeRequest
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_families.*
import kotlinx.android.synthetic.main.fragment_families.goneL
import kotlinx.android.synthetic.main.fragment_families.view.*
import java.util.*


class RelativeFragment : Fragment() {
    private lateinit var col: ColorStateList
    private lateinit var mDet: DatePickerDialog.OnDateSetListener
    private lateinit var viewModel: ReferenceViewModel
    private var relativeId = 0
    private var relative = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_families, container, false)
        col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))

        val buttonF = root.findViewById<Button>(R.id.buttonFamilies)
        buttonF.setOnClickListener {

            AddReferenceFragment.list.add(
                RelativeRequest(
                    relativeId,
                    root.text_families_date.text.toString(),
                    root.edit_families.text.toString(),
                    relative
                )
            )
            findNavController().popBackStack()

        }
        return root
    }

    override fun onStart() {
        super.onStart()
        getTextFamilies()
        getAdapterA()
        getAdapterB()
    }

    private fun getAdapterB() {
        viewModel.relatives().observe(this, Observer {
            text_families_who.setAdapter(
                ArrayAdapter<MessagesPersonsModel>(
                    context!!,
                    android.R.layout.simple_dropdown_item_1line,
                    it
                )
            )
            text_families_who.keyListener = null;

            text_families_who.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    text_families_who.showDropDown()
                    val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    text_families.defaultHintTextColor = col
                    relativeId = (parent.getItemAtPosition(position) as MessagesPersonsModel).id
                    relative = (parent.getItemAtPosition(position) as MessagesPersonsModel).name

                }
            text_families_who.setOnClickListener {
                text_families_who.showDropDown()
            }
            text_families_who.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
                if (b) {
                    try {
                        text_families_who.showDropDown()
                    } catch (e: Exception) {
                        println()
                    }
                }
            }
        })

    }

    private fun getAdapterA() {
        text_families_date.keyListener = null
        text_families_date.setOnFocusChangeListener { view, b ->
            if (b) {
                mDet = DatePickerDialog.OnDateSetListener { dateP: DatePicker, year, month, day ->
//                    val data = "$day/$month/$year"
                    val data = MyUtils.convertDate(day,month,year)
                    text_families_date.setText(data)
                }
                val calendar = Calendar.getInstance()
                val year: Int = calendar.get(Calendar.YEAR)
                val month: Int = calendar.get(Calendar.MONTH)
                val dey: Int = calendar.get(Calendar.DAY_OF_MONTH)
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                text_date.defaultHintTextColor = col

                val date = context?.let {
                    DatePickerDialog(
                        it,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDet,
                        year,
                        month,
                        dey
                    )
                }
                date?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                date?.show()
                goneL.requestFocus()
            }
        }
    }

    private fun getTextFamilies() {
        edit_families.setOnFocusChangeListener { _, _ ->
            text_families_name.defaultHintTextColor = col

        }
    }
}
