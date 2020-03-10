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
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import kotlinx.android.synthetic.main.fragment_families.*
import kotlinx.android.synthetic.main.fragment_families.goneL
import java.util.*

class FamiliesFragment : Fragment() {
    private lateinit var col: ColorStateList
    private lateinit var mDet: DatePickerDialog.OnDateSetListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val root = inflater.inflate(R.layout.fragment_families, container, false)
         col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))

         val buttonF = root.findViewById<Button>(R.id.buttonFamilies)
         buttonF.setOnClickListener { b ->
            findNavController().navigate(R.id.navigation_final_references)
        }
        return root
    }

    override fun onStart() {
        super.onStart()

        getTextFamilies()
        getAdapterA()
        getAdapterB()

    }
    private fun getAdapterB(){
        val who = arrayOf("Никем")

        val adapterP =
            ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, who)
        text_families_who.setAdapter(adapterP)
        text_families_who.setKeyListener(null);

        text_families_who.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                text_families_who.showDropDown()
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                text_families.defaultHintTextColor = col
                val selectedItem = parent.getItemAtPosition(position).toString()

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
    }

    private fun getAdapterA(){
        text_families_date.setKeyListener(null)
        text_families_date.setOnFocusChangeListener { view, b ->
            if (b){
                mDet = DatePickerDialog.OnDateSetListener{dateP: DatePicker, year, month,day ->
                    val data: String = month.toString() + "/" + day + "/" + year
                    text_families_date.setText(data)
                }
                val calendar = Calendar.getInstance()
                val year: Int = calendar.get(Calendar.YEAR)
                val month: Int = calendar.get(Calendar.MONTH)
                val dey: Int = calendar.get(Calendar.DAY_OF_MONTH)
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                text_date.defaultHintTextColor = col

                val date = context?.let { DatePickerDialog(it, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDet, year, month, dey) }
                date?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                date?.show()
                goneL.requestFocus()
            }
        }
    }

    private fun getTextFamilies(){
        edit_families.setOnFocusChangeListener{ pref, boolean ->
            if (boolean){
                text_families_name.defaultHintTextColor = col
            }else{
                text_families_name.defaultHintTextColor = col
            }
        }
    }
}
