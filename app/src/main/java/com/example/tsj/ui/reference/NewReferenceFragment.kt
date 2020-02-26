package com.example.tsj.ui.reference


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import kotlinx.android.synthetic.main.fragment_new_reference.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class NewReferenceFragment : Fragment() {
    private lateinit var col: ColorStateList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_reference, container, false)
        col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
        (activity as AppCompatActivity).supportActionBar!!.show()

        val layout_new = root.findViewById<LinearLayout>(R.id.layout_new_Ref)
        layout_new.setOnClickListener { v ->
            findNavController().navigate(R.id.navigation_families)
        }
        return root
    }

    override fun onStart() {
        super.onStart()

        getEditRef()
        getEditReferenceS()
        getEditReferenceDo()

    }

    private fun getEditRef() {
        edit_ref.setOnFocusChangeListener { pref, boolean ->
            if (boolean) {
                lRef.defaultHintTextColor = col
            } else {
                lRef.defaultHintTextColor = col
            }
        }
    }

    private fun getEditReferenceS() {
        editReferenceS.setKeyListener(null);
        editReferenceS.setOnFocusChangeListener { view, b ->
            if (b) {
                val cldr = Calendar.getInstance()
                val day = cldr.get(Calendar.DAY_OF_MONTH)
                val month = cldr.get(Calendar.MONTH)
                val year = cldr.get(Calendar.YEAR)
                val picker: DatePickerDialog
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                referenceS.defaultHintTextColor = col
                picker =
                    DatePickerDialog(activity!!, { datePicker, year1, monthOfYear, dayOfMonth ->
                        if (monthOfYear + 1 < 10) {
                            editReferenceS.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                        } else {
                            editReferenceS.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                        }
                    }, year, month, day)
                picker.show()
                goneL.requestFocus()
            }
        }
    }

    private fun getEditReferenceDo() {
        editReferenceDo.setKeyListener(null);
        editReferenceDo.setOnFocusChangeListener { view, b ->
            if (b) {
                if (editReferenceS.text.length == 0) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                } else {
                    if (b) {
                        val cldr = Calendar.getInstance()
                        val day = cldr.get(Calendar.DAY_OF_MONTH)
                        val month = cldr.get(Calendar.MONTH)
                        val year = cldr.get(Calendar.YEAR)
                        val col =
                            ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                        referenceDo.defaultHintTextColor = col
                        val picker: DatePickerDialog
                        picker =
                            DatePickerDialog(
                                activity!!,
                                { datePicker, year1, monthOfYear, dayOfMonth ->
                                    if (monthOfYear + 1 < 10) {
                                        editReferenceDo.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                                    } else {
                                        editReferenceDo.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                                    }

                                },
                                year,
                                month,
                                day
                            )
                        try {
                            val timeS =
                                SimpleDateFormat("dd/MM/yyyy").parse(editReferenceS.text.toString())
                                    .getTime()
                            picker.datePicker.minDate = timeS + 1000

                        } catch (e: Exception) {
                            picker.datePicker.minDate = System.currentTimeMillis() - 1000
                        }

                        picker.show()
                        goneL.requestFocus()
                    }
                }
            }
        }
    }
}