package com.timelysoft.tsjdomcom.ui.reference


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.MessagesPersonsModel
import com.timelysoft.tsjdomcom.service.model.RelativeModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_relative.*
import kotlinx.android.synthetic.main.fragment_relative.view.*
import java.lang.Exception
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
        val root = inflater.inflate(R.layout.fragment_relative, container, false)
        initArguments()
        initViews(root)
        initData(root)

        return root
    }

    private fun isValid(): Boolean {
        var valid = true
        if (relative_name.text.toString().isEmpty()) {
            relative_name_out.error = "ФИО не должно быть пустым"
            valid = false
        } else {
            relative_name_out.isErrorEnabled = false
        }

        if (relative_date.text.toString().isEmpty()) {
            relative_date_out.error = "Поле не должно быть пустым"
            valid = false
        } else {
            relative_date_out.isErrorEnabled = false
        }

        return valid
    }

    override fun onStart() {
        super.onStart()
        initHint()
        check()

    }

    private fun check() {
        if (position != -1) {
            buttonFamilies.text = "Обновить"
            (activity as AppCompatActivity).supportActionBar?.title = "Обновить"
        }
    }

    private fun initHint() {
        if (relative_name.text.isNotEmpty()) {

            relative_date_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            relative_name_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }


    private fun initArguments() {

        position = try {
            arguments!!.getInt("position")
        } catch (e: Exception) {
            -1
        }

    }


    private fun initData(root: View) {
        MainActivity.alert.show()
        viewModel.relatives().observe(this, Observer {
            MainActivity.alert.hide()
            val adapterRelative =
                ArrayAdapter<MessagesPersonsModel>(context!!, R.layout.item_spinner_adapter, it)
            adapterRelative.setDropDownViewResource(R.layout.item_spinner_dropdown)
            root.relative_relative.adapter = adapterRelative
            if (position != -1) {
                val id = AddUpdateReferenceFragment.list[position].relativeId
                it.forEachIndexed { index, model ->
                    if (model.id == id) {
                        root.relative_relative.setSelection(index + 1)
                    }
                }


            }
        })
    }

    private fun initViews(root: View) {
        if (position != -1) {
            root.relative_date.setText(MyUtils.toMyDate(AddUpdateReferenceFragment.list[position].dateOfBirth))
            root.relative_name.setText(AddUpdateReferenceFragment.list[position].fullName)

        }
        root.buttonFamilies.setOnClickListener {
            try {
                relativeId = (root.relative_relative.selectedItem as MessagesPersonsModel).id
                relative = (root.relative_relative.selectedItem as MessagesPersonsModel).name
            } catch (e: Exception) {
                root.relative_relative.error = "Заполните поле"
            }

            if (isValid()) {
                if (position == -1) {
                    AddUpdateReferenceFragment.list.add(
                        RelativeModel(
                            relativeId,
                            MyUtils.toServerDate(root.relative_date.text.toString()),
                            root.relative_name.text.toString(),
                            relative
                        )
                    )
                } else {
                    AddUpdateReferenceFragment.list[position] = RelativeModel(
                        relativeId,
                        MyUtils.toServerDate(root.relative_date.text.toString()),
                        root.relative_name.text.toString(),
                        relative
                    )
                }

                findNavController().popBackStack()
            }
        }

        root.relative_name.setOnFocusChangeListener { _, _ ->
            root.relative_name_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))

        }

        root.relative_date.keyListener = null
        root.relative_date.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val mDet = DatePickerDialog.OnDateSetListener { _: DatePicker, year, month, day ->
                    val data = MyUtils.convertDate(day, month, year)
                    root.relative_date.setText(data)
                }
                val calendar = Calendar.getInstance()
                val year: Int = calendar.get(Calendar.YEAR)
                val month: Int = calendar.get(Calendar.MONTH)
                val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
                val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.relative_date_out.defaultHintTextColor = col
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
                root.relative_date.clearFocus()
            }
            if (!hasFocus && root.relative_date.text.isNotEmpty()) {
                root.relative_date_out.isErrorEnabled = false
            }
        }
    }
}
