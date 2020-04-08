package com.timelysoft.tsjdomcom.ui.reference


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.families.FamilyAdapter
import com.timelysoft.tsjdomcom.adapters.families.FamilyListener
import com.timelysoft.tsjdomcom.service.model.PersonModel
import com.timelysoft.tsjdomcom.service.model.RelativeModel
import com.timelysoft.tsjdomcom.service.request.CertificateRequest
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_new_reference.*
import kotlinx.android.synthetic.main.fragment_new_reference.view.*
import java.util.*
import kotlin.collections.ArrayList


class AddUpdateReferenceFragment : Fragment(), FamilyListener {
    private lateinit var relativeAdapter: FamilyAdapter
    private lateinit var viewModel: ReferenceViewModel
    private var update = false
    val certificateRequest = CertificateRequest()
    private var mLastClickTime: Long = 0

    init {
        if (certificateRequest.person == null)
            certificateRequest.person = PersonModel()
        if (certificateRequest.relatives == null)
            certificateRequest.relatives = listOf()
    }

    companion object {
        val relativesList = ArrayList<RelativeModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_reference, container, false)
        initArguments()
        initViews(root)

        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)

        return root
    }

    private fun validate(): Boolean {
        var valid = true
        if (reference_date.text.toString().isEmpty()) {
            reference_date_out.error = "Вы не выбрали дату"
            valid = false
        } else {
            reference_date_out.isErrorEnabled = false
        }

        if (reference_name.text.toString().isEmpty()) {
            reference_name_out.error = "Поле не должно быть пустым"
            valid = false
        } else {
            reference_name_out.isErrorEnabled = false
        }

        return valid
    }

    private fun initHint() {
        if (reference_name.text.isNotEmpty()) {
            reference_name_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            reference_date_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun initViews(root: View) {
        root.reference_add_relative.setOnClickListener {
            findNavController().navigate(R.id.navigation_families)
        }
        relativeAdapter = FamilyAdapter(this)
        root.relative_rv.adapter = relativeAdapter
        relativeAdapter.update(relativesList)

        root.reference_name.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.reference_name.text.isNotEmpty()) {
                root.reference_name_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.reference_name_out.isErrorEnabled = false
            }
        }

        root.reference_save.setOnClickListener {
            MyUtils.hideKeyboard(activity!!, view!!)
            if (validate()) {
                certificateRequest.relatives = relativesList
                certificateRequest.person.fullName = reference_name.text.toString()
                certificateRequest.person.dateOfBirth = MyUtils.toServerDate(reference_date.text.toString())
                if (!update) {
                    MainActivity.alert.show()
                    viewModel.addReferences(certificateRequest).observe(this, Observer {
                        if (it) {
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Ошибка при отправке данных",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        MainActivity.alert.hide()
                    })
                } else {
                    MainActivity.alert.show()
                    viewModel.updateReference(certificateRequest).observe(this, Observer {
                        if (it) {
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Ошибка при отправке данных",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        MainActivity.alert.hide()
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
            (activity as AppCompatActivity?)!!.supportActionBar?.title = "Обновленная справка"
        }
        if (certificateRequest.id != null && certificateRequest.id != 0 && !update) {
            update = true
            reference_save.text = "Обновить"
            (activity as AppCompatActivity?)!!.supportActionBar?.title = "Обновленная справка"
            viewModel.reference(certificateRequest.id).observe(this, Observer {
                certificateRequest.person.id = it.person?.id
                reference_name.setText(it.person?.fullName)
                reference_date.setText(MyUtils.toMyDate(it.person!!.dateOfBirth))
                it.relatives?.forEach { item ->
                    relativesList.add(item)
                }
                relativeAdapter.update(relativesList)
                initHint()
            })
        }
        initHint()
    }

    private fun initArguments() {
        certificateRequest.placementId = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        certificateRequest.id = try {
            arguments!!.getInt("referenceId")
        } catch (e: Exception) {
            0
        }
    }

    private fun getEditReferenceS() {
        reference_date.keyListener = null
        // Временный тракеч. Приложение падает.
        reference_date.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            MyUtils.hideKeyboard(activity!!, view!!)
            if (hasFocus) {
                // Педотврощает двоной клик на editText
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                val cldr = Calendar.getInstance()
                val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                reference_date_out.defaultHintTextColor = col
                val picker =
                    DatePickerDialog(
                        activity!!, { _, year1, monthOfYear, dayOfMonth ->
                            reference_date.setText(
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
                reference_date.clearFocus()
            }
            if (!hasFocus && reference_date.text.isNotEmpty()) {
                reference_date_out.isErrorEnabled = false
            }
        }
    }

    override fun onClickDelete(id: Int) {
        relativesList.removeAt(id)
        relativeAdapter.update(relativesList)
    }

    override fun onClickItem(id: Int) {
        val bundle = Bundle()
        bundle.putInt("position", id)
        findNavController().navigate(R.id.navigation_families, bundle)
    }
}