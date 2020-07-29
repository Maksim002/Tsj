package com.timelysoft.tsjdomcom.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.request.user.Edit
import kotlinx.android.synthetic.main.fragment_edit.*
import java.lang.Exception

class EditFragment : Fragment() {

    private var viewModel = UserViewModel()
    private lateinit var model: Edit

    private lateinit var password: String
    private lateinit var confirmPassword: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArgument()
    }

    private fun initArgument() {
        val editId = try {
            arguments!!.getInt("editId")
        } catch (e: Exception) {
            0
        }

        viewModel.editId(editId).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    edit_surname_out.setText(data!!.person!!.lastName)
                    edit_name_out.setText(data.person!!.firstName)
                    edit_patronymic_out.setText(data.person!!.middleName)
                    edit_email_out.setText(data.person!!.email)
                    edit_password_out.setText(data.person!!.newPassword)
                    edit_repeat_password_out.setText(data.person!!.confirmPassword)
                }
                Status.ERROR, Status.NETWORK -> {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
        edit_save.setOnClickListener {
            model = Edit(edit_name_out.text.toString(), edit_patronymic_out.text.toString(), edit_surname_out.text.toString(), edit_password_out.text.toString(), edit_repeat_password_out.text.toString(),
                editId, edit_email_out.text.toString())

            viewModel.edit(model).observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                when (result.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}