package com.timelysoft.tsjdomcom.ui.requestForConnect


import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_connect.*

class RequestForConnectFragment : Fragment() {

    private lateinit var viewModel: RequestForConnectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RequestForConnectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_connect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateEditText()

        connect_name_edit.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && connect_name_edit.text!!.isNotEmpty()) {
                connect_name.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }
    }


    private fun validateEditText() {
        connect_send_btn.setOnClickListener {


            if (isValid()) {
                val count = try {
                    connect_count_edit.text.toString().toInt()
                } catch (e: Exception) {
                    0
                }

                val body =
                    RequestForConnectModel(
                        connect_name_edit.text.toString(),
                        connect_tsj_edit.text.toString(),
                        connect_address_edit.text.toString(),
                        connect_number_edit.text.toString(),
                        connect_email_edit.text.toString(),
                        count,
                        connect_feedback_edit.text.toString()
                    )
                MainActivity.alert.show()

                viewModel.requestForConnectionN(body).observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    MainActivity.alert.hide()
                    when(result.status){
                        Status.SUCCESS ->{
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }
                        Status.ERROR, Status.NETWORK ->{
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun isValid(): Boolean {

        var valid = true

        if (connect_address_edit.text.toString().isEmpty()) {
            valid = false
            connect_address.error = "Заполните адрес"
        } else {
            connect_address.isErrorEnabled = false
        }


        if (connect_number_edit.text.toString().isEmpty()) {
            connect_number.error = "Заполните номер"
            valid = false
        } else {
            connect_number.isErrorEnabled = false
        }
        val count = try {
            connect_count_edit.text.toString().toInt()
        } catch (e: Exception) {
            0
        }
        if (count == 0) {
            connect_count.error = "Заполните количество квартир"
            valid = false
        } else {
            connect_count.isErrorEnabled = false
        }

        if (connect_name_edit.text.toString().isEmpty()) {
            connect_name.error = "Заполните ваше имя"
            valid = false
        } else {
            connect_name.isErrorEnabled = false
        }

        if (!MyUtils.emailValidate(connect_email_edit.text.toString())) {
            connect_email.error = "Не правильный email"
            valid = false
        } else {
            connect_email.isErrorEnabled = false
        }

        if (connect_tsj_edit.text.toString().isEmpty()) {
            connect_tsj.error = "Заполните наименование"
            valid = false
        } else {
            connect_tsj.isErrorEnabled = false
        }

        if (connect_feedback_edit.text.toString().isEmpty()) {
            connect_feedback.error = "Заполните ваше пожелание"
            valid = false
        } else {
            connect_feedback.isErrorEnabled = false
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        setColor()
    }

    private fun setColor() {
        connect_name_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_name_edit.text!!.isNotEmpty()) {
                connect_name.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_name_edit.text!!.isEmpty()) {
                connect_name.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }

        connect_tsj_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_tsj_edit.text!!.isNotEmpty()) {
                connect_tsj.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_tsj_edit.text!!.isEmpty()) {
                connect_tsj.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }

        connect_address_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_address_edit.text!!.isNotEmpty()) {
                connect_address.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_address_edit.text!!.isEmpty()) {
                connect_address.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }

        connect_number_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_address_edit.text!!.isNotEmpty()) {
                connect_number.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_number_edit.text!!.isEmpty()) {
                connect_number.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }

        connect_email_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_email_edit.text!!.isNotEmpty()) {
                connect_email.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_email_edit.text!!.isEmpty()) {
                connect_email.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }

        connect_count_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_count_edit.text!!.isNotEmpty()) {
                connect_count.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_count_edit.text!!.isEmpty()) {
                connect_count.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }

        connect_feedback_edit.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || connect_feedback_edit.text!!.isNotEmpty()) {
                connect_feedback.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            } else if (connect_feedback_edit.text!!.isEmpty()) {
                connect_feedback.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
            }
        }
    }
}
