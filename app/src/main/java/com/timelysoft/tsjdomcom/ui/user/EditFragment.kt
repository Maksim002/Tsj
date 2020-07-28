package com.timelysoft.tsjdomcom.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.request.user.EditModel
import com.timelysoft.tsjdomcom.service.model.user.UserChairmanModel
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {

    private var viewModel = UserViewModel()

    private lateinit var date: UserChairmanModel

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
        date = try {
            arguments!!.getSerializable("user")
        }catch (e: Exception){
            null
        } as UserChairmanModel

        edit_email_out.setText(date.email)
        edit_name_out.setText(date.name.substring(0, 8))
        edit_surname_out.setText(date.name.substring(8, 14))
        edit_patronymic_out.setText(date.name.substring(14, 20))

        val model = EditModel(
            edit_name_out.text.toString(),
            edit_patronymic_out.text.toString(),
            edit_surname_out.text.toString(),
            edit_password_out.text.toString(),
            edit_repeat_password_out.text.toString(),
            date.id,
            date.email
        )

        edit_save.setOnClickListener {
            viewModel.edit(model).observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                when(result.status){
                    Status.SUCCESS ->{
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR, Status.NETWORK ->{
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
}