package com.example.tsj.ui.requestForConnect


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

            val address = connect_address_edit.text.toString()
            val number = connect_number_edit.text.toString()

            var count = try {
                connect_count_edit.text.toString().toInt()
            } catch (e: Exception) {
                0
            }

            val name = connect_name_edit.text.toString()
            val email = connect_email_edit.text.toString()
            val association = connect_tsj_edit.text.toString()
            val feedback = connect_feedback_edit.text.toString()

            val body =
                RequestForConnectModel(name, association, address, number, email, count, feedback)

            if (validate(address, number, count, name, email, association)) {
                viewModel.requestForConnection(body).observe(this, Observer {
                    if (it) {
                        Toast.makeText(context, "Ваше заявка отправлена!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
// address number count name email associton

    fun validate(
        address: String,
        number: String,
        count: Int,
        name: String,
        email: String,
        association: String
    ): Boolean {

        var boolean = false

        if (address.isEmpty()) {
            boolean = false
            connect_address.error = "Заполните адрес"
        } else {
            boolean = true
            connect_address.error = null
        }


        if (number.isEmpty()) {
            connect_number.error = "Заполните номер"
            boolean = false
        } else {
            connect_number.error = null
            boolean = true
        }

        if (count == 0) {
            connect_count.error = "Заполните количество квартир"
            boolean = false
        } else {
            connect_count.error = null
            boolean = true
        }

        if (name.isEmpty()) {
            connect_name.error = "Заполните ваше имя"
            boolean = false
        } else {
            connect_name.error = null
            boolean = true
        }

        if (!MyUtils.emailValidate(email)) {
            connect_email.error = "Не правильный email"
            boolean = false
        } else {
            connect_email.error = null
            boolean = true
        }

        if (association.isEmpty()) {
            connect_tsj.error = "Заполните наименование"
            boolean = false
        } else {
            connect_tsj.error = null
            boolean = true
        }

        return boolean
    }

}
