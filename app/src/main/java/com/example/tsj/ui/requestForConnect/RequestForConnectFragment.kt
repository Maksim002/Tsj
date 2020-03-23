package com.example.tsj.ui.requestForConnect


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.model.RequestForConnectModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_connect.*

/**
 * A simple [Fragment] subclass.
 */
class RequestForConnectFragment : Fragment() {

    private lateinit var viewModel: RequestForConnectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(RequestForConnectViewModel::class.java)
        return inflater.inflate(R.layout.fragment_connect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        validateEditText()
    }

    private fun initViews() {

    }

    private fun validateEditText() {
        connect_send_btn.setOnClickListener {


            if (validate()) {
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
                viewModel.requestForConnection(body).observe(this, Observer {
                    if (it) {
                        Toast.makeText(context, "Ваше заявка отправлена!", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()

                    } else {
                        Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                    MainActivity.alert.hide()
                })
            }
        }
    }

    private fun validate(): Boolean {

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

}