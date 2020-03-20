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
import com.example.tsj.service.model.PersonModel
import com.example.tsj.service.model.RelativeModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_message_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_new_reference.*
import kotlinx.android.synthetic.main.fragment_new_reference.view.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class AddUpdateReferenceFragment : Fragment(), FamilyListener {
    private lateinit var refAdapter: FamilyAdapter
    private lateinit var viewModel: ReferenceViewModel
    private var update = false
    val data = CertificateRequest()

    init {
        if (data.person == null)
            data.person = PersonModel()
        if (data.relatives == null)
            data.relatives = listOf()
    }

    companion object {
        val list = ArrayList<RelativeModel>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_new_reference, container, false)
        initArguments()
        initViews(root)

        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)

        return root
    }

    private fun validate(): Boolean{
        var valid = true
        if (editReferenceS.getText().toString().length == 0) {
            referenceS.error = "Вы не выбрили дату"
            valid = false
        }else{
            referenceS.setErrorEnabled(false)
        }

        if (edit_ref.getText().toString().length == 0) {
            lRef.setError("Поле не должно быть пустым")
            valid = false
        }else{
            lRef.setErrorEnabled(false)
        }

        return valid
    }

    private fun initHint(){
        if (edit_ref.text.isNotEmpty()){
            lRef.defaultHintTextColor =  ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            referenceS.defaultHintTextColor =  ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun initViews(root: View) {
        root.reference_add_relative.setOnClickListener {
            findNavController().navigate(R.id.navigation_families)
        }
        refAdapter = FamilyAdapter(this)
        root.familiesRecyclerView.adapter = refAdapter
        refAdapter.update(list)

        root.edit_ref.setOnFocusChangeListener { _, _ ->
            root.lRef.defaultHintTextColor =  ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        root.reference_save.setOnClickListener {
            MyUtils.hideKeyboard(activity!!,view!!)
            if (validate()){
                data.relatives = list
                data.person.fullName = edit_ref.text.toString()
                data.person.dateOfBirth = MyUtils.toServerDate(editReferenceS.text.toString())
                if (!update) {
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
                } else {
                    viewModel.updateReference(data).observe(this, Observer {
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

        }
    }

    override fun onStart() {
        super.onStart()
        getEditReferenceS()
        if (update) {
            reference_save.text = "Обновить"
        }
        if (data.id != null && data.id != 0 && !update) {
            update = true
            reference_save.text = "Обновить"
            viewModel.reference(data.id).observe(this, Observer {
                data.person.id = it.person.id
                edit_ref.setText(it.person.fullName)
                editReferenceS.setText(MyUtils.toMyDate(it.forDate))
                it.relatives.forEach { item ->
                    list.add(item)
                }
                refAdapter.update(list)
                initHint()
            })
        }
    }

    private fun initArguments() {
        data.placementId = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        data.id = try {
            arguments!!.getInt("referenceId")
        } catch (e: Exception) {
            0
        }
    }

    private fun getEditReferenceS() {
        editReferenceS.keyListener = null
    // Временный тракеч. Приложение падает.
            editReferenceS.setOnFocusChangeListener { _, b ->
                if (b) {
                        val cldr = Calendar.getInstance()
                        val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        referenceS.defaultHintTextColor = col
                        val picker =
                            DatePickerDialog(activity!!, { _, year1, monthOfYear, dayOfMonth ->
                                    editReferenceS.setText(MyUtils.convertDate(dayOfMonth, monthOfYear, year1)) },
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

    override fun onClickItem(id: Int) {
        val bundle = Bundle()
        bundle.putInt("position", id)
        findNavController().navigate(R.id.navigation_families, bundle)
    }


}