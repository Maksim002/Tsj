package com.timelysoft.tsjdomcom.ui.profile


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import kotlinx.android.synthetic.main.fragment_change_password.*


class ChangePassword : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHint()
        change_password_btn.setOnClickListener {
            if (validate()) {
                val model = ChangePasswordModel()
                model.oldPassword = change_password_old.text.toString()
                model.newPassword = change_password_new.text.toString()
                model.confirmPassword = change_password_accept.text.toString()
                MainActivity.alert.show()

                viewModel.changePasswordN(model).observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    MainActivity.alert.hide()
                    when(result.status){
                        Status.SUCCESS ->{
                            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
                            findNavController().popBackStack()
                        }
                        Status.ERROR, Status.NETWORK ->{
                            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
                            change_password_old_input.error = "Неверный старый пароль"
                        }
                    }
                })
            }
        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (change_password_old.text.toString().isEmpty()) {
            change_password_old_input.error = "Поле не должно быть пустым"
            valid = false
        } else {
            change_password_old_input.isErrorEnabled = false
        }

        if (change_password_new.text.toString().isEmpty()) {
            change_password_new_input.error = "Поле не должно быть пустым"
            valid = false
        } else {
            change_password_new_input.isErrorEnabled = false

        }

        if (change_password_accept.text.toString().isEmpty()) {
            change_password_accept_input.error = "Пароль не должен быть пустым"
            valid = false
        } else {
            change_password_accept_input.isErrorEnabled = false
        }

        if (change_password_new.text.toString() != change_password_accept.text.toString()) {
            valid = false
            change_password_new_input.error = "Пароль не совпадает"
            change_password_accept_input.error = "Пароль не совпадает"
            Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_LONG).show()
        }
        return valid
    }

    private fun initHint() {
        change_password_old.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (!hasFocus && change_password_old.text!!.isNotEmpty()) {
                change_password_old_input.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
        change_password_new.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (!hasFocus && change_password_new.text!!.isNotEmpty()) {
                change_password_new_input.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
        change_password_accept.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
            if (!hasFocus && change_password_accept.text!!.isNotEmpty()) {
                change_password_accept_input.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
    }
}
