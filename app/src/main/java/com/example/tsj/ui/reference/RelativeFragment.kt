package com.example.tsj.ui.reference


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.service.model.RelativeModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_families.*
import kotlinx.android.synthetic.main.fragment_families.view.*
import java.lang.Exception
import java.util.*


class RelativeFragment : Fragment() {

    private lateinit var viewModel: ReferenceViewModel
    private var relativeId = 0
    private var relative = ""
    private var position = -1
    private var mLastClickTime: Long = 0

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

    private fun validate(): Boolean {
        var valid = true
        if (edit_families.text.toString().isEmpty()) {
            text_families_name.error = "ФИО не должно быть пустым"
            valid = false
        } else {
            text_families_name.isErrorEnabled = false
        }

        if (text_families_date.text.toString().isEmpty()) {
            text_date.error = "Поле не должно быть пустым"
            valid = false
        } else {
            text_date.isErrorEnabled = false
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
            buttonFamilies.setText("Обновить")
            (activity as AppCompatActivity).supportActionBar?.setTitle("Обновить")
        }
    }

    private fun initHint() {
        if (edit_families.text.isNotEmpty()) {

            text_date.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            text_families_name.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }


    private fun initArguments() {

        position = try {
            arguments!!.getInt("position")
        } catch (e: java.lang.Exception) {
            -1
        }
    }


    private fun initData(root: View) {
        MainActivity.alert.show()
        viewModel.relatives().observe(this, Observer {
            val adapter = ArrayAdapter<MessagesPersonsModel>(
                context!!, android.R.layout.simple_spinner_item, it)
            adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1)
            root.bind_add_request.setAdapter(adapter)
            MainActivity.alert.hide()
            if (position != -1) {
                val id = AddUpdateReferenceFragment.list[position].relativeId
                it.forEachIndexed { index, model ->
                    if (model.id == id) {
                        root.bind_add_request.setSelection(index + 1)
                    }
                }


            }
        })
    }

    private fun initViews(root: View) {
        if (position != -1) {
            root.text_families_date.setText(MyUtils.toMyDate(AddUpdateReferenceFragment.list[position].dateOfBirth))
            root.edit_families.setText(AddUpdateReferenceFragment.list[position].fullName)

        }
        root.findViewById<Button>(R.id.buttonFamilies).setOnClickListener {
            if (validate()) {
                try {
                    relativeId = (root.bind_add_request.selectedItem as MessagesPersonsModel).id
                    relative = (root.bind_add_request.selectedItem as MessagesPersonsModel).name
                } catch (e: Exception) {
                    root.bind_add_request.error = "Заполните поле"
                }

                if (validate()) {
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
            }

            root.edit_families.setOnFocusChangeListener { _, _ ->
                root.text_families_name.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))

            }

            root.text_families_date.keyListener = null
            root.text_families_date.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
                if (hasFocus) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return@setOnClickListener
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    val mDet =
                        DatePickerDialog.OnDateSetListener { _: DatePicker, year, month, day ->
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
                    root.text_families_date.clearFocus()
                }
                if (!hasFocus && root.text_families_date.text.isNotEmpty()) {
                    root.text_date.isErrorEnabled = false
                }
            }
        }
    }
}
