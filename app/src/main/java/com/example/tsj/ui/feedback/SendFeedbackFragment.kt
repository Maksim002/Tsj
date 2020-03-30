package com.example.tsj.ui.feedback


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import com.example.tsj.service.request.FeedbackRequest
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_send_feedback.*

class SendFeedbackFragment : Fragment() {
    private lateinit var viewModel: FeedbackViewModel
    private var name: String = " "
    private var email: String = " "
    private var letter: String = " "
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(FeedbackViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_send_feedback, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_your_mail.setText(AppPreferences.email.toString())
        edit_your_mail.tag = edit_your_mail.keyListener
        edit_your_mail.keyListener = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.feedback_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_feedback -> {
                senFeedBack()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun senFeedBack() {
        if (AppPreferences.isLogined) {
            name = edit_to_whom.text.toString()
            email = AppPreferences.email.toString()
            letter = edit_write_a_letter.text.toString()
        } else {
            name = edit_to_whom.text.toString()
            email = edit_your_mail.text.toString()
            letter = edit_write_a_letter.text.toString()
        }

        MyUtils.hideKeyboard(activity!!, view!!)
        if (validate()) {
            MainActivity.alert.show()
            val body = FeedbackRequest(
                name, email, letter
            )
            viewModel.sendFeedback(body).observe(this, Observer {
                if (it) {
                    Toast.makeText(context, "Ваше письмо отправлено!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ошибка!", Toast.LENGTH_SHORT).show()
                }
                MainActivity.alert.hide()
            })
        }

    }

    private fun validate(): Boolean {
        var valid = true
        if (edit_to_whom.text.toString().isEmpty()) {
            edit_to_whom_edit.error = "Поле не должно быть пустым"
            valid = false
        } else {
            edit_to_whom_edit.isErrorEnabled = false
        }

        if (!AppPreferences.isLogined) {
            if (!MyUtils.emailValidate(edit_your_mail.text.toString())) {
                valid = false
                edit_your_mail_edit.error = "Не правильный email"
            } else {
                edit_your_mail_edit.isErrorEnabled = false
            }
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        intColor()
        initHint()
    }

    fun initHint() {
        edit_your_mail_edit.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }

    private fun intColor() {
        edit_to_whom.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edit_to_whom.text!!.isNotEmpty()) {
                edit_to_whom_edit.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }

        edit_your_mail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edit_your_mail.text!!.isNotEmpty()) {
                edit_your_mail_edit.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }

        edit_write_a_letter.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edit_write_a_letter.text!!.isNotEmpty()) {
                edit_write_a_letter_edit

                    .defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
    }
}
