package com.example.tsj.ui.reference


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.families.FamilyAdapter
import com.example.tsj.adapters.families.FamilyListener
import com.example.tsj.service.request.CertificateRequest
import com.example.tsj.service.request.PersonRequest
import com.example.tsj.service.request.RelativeRequest
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_new_reference.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class AddReferenceFragment : Fragment(), FamilyListener {
    private lateinit var col: ColorStateList
    private lateinit var refAdapter: FamilyAdapter
    private lateinit var recyclerViewR: RecyclerView
    private lateinit var viewModel: ReferenceViewModel
    private var placementId = 0

    companion object {
        val list = ArrayList<RelativeRequest>()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_reference, container, false)
        initArguments()
        initViews(root)
        initData(root)

        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)




        return root
    }

    private fun initData(root: View) {

    }

    private fun initViews(root: View) {
        col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        val layout_new = root.findViewById<TextView>(R.id.layout_new_Ref)
        layout_new.setOnClickListener {
            findNavController().navigate(R.id.navigation_families)
        }
        recyclerViewR = root.findViewById(R.id.familiesRecyclerView)
        refAdapter = FamilyAdapter(this)
        recyclerViewR.adapter = refAdapter
        refAdapter.update(list)

        edit_ref.setOnFocusChangeListener { _, _ ->
            lRef.defaultHintTextColor = col
        }

        reference_save.setOnClickListener {
            //
            val data = CertificateRequest()
            data.relatives = list
            val person = PersonRequest()
            person.fullName = edit_ref.text.toString()
            person.dateOfBirth = MyUtils.toServerDate(editReferenceS.text.toString())
            data.person = person
            data.placementId = placementId
            viewModel.addReferences(data).observe(this, Observer {
                if (it) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Произошла ошибка при отправке данных",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()

        getEditReferenceS()

    }

    private fun initArguments() {
        placementId = try {
            arguments!!.getInt("id")
        } catch (e: Exception) {
            0
        }
    }


    private fun getEditReferenceS() {
        editReferenceS.keyListener = null
        editReferenceS.setOnFocusChangeListener { _, b ->
            if (b) {
                val cldr = Calendar.getInstance()
                val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                referenceS.defaultHintTextColor = col
                val picker =
                    DatePickerDialog(
                        activity!!,
                        { _, year1, monthOfYear, dayOfMonth ->
                            editReferenceS.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear,
                                    year1
                                )
                            )

                        },
                        cldr.get(Calendar.YEAR),
                        cldr.get(Calendar.MONTH),
                        cldr.get(Calendar.DAY_OF_MONTH)
                    )
                picker.show()
                goneL.requestFocus()
            }
        }


    }

    override fun onClickDelete(id: Int) {
        list.removeAt(id)
        refAdapter.update(list)
    }


}