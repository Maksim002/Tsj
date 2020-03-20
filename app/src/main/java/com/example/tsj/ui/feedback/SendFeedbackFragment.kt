package com.example.tsj.ui.feedback


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tsj.R
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_new_reference.*
import kotlinx.android.synthetic.main.fragment_send_feedback.*

class SendFeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_send_feedback, container, false)
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
        MyUtils.hideKeyboard(activity!!, view!!)
        if (validate()){

        }
    }
    private fun validate(): Boolean{
        var valid = true
        if (edit_to_whom.getText().toString().length == 0) {
            edit_to_whom_edit.setError("Поле не должно быть пустым")
            valid = false
        }else{
            edit_to_whom_edit.setErrorEnabled(false)
        }

        if (edit_your_mail.getText().toString().length == 0) {
            edit_your_mail_edit.setError("Поле не должно быть пустым")
            valid = false
        }else{
            edit_your_mail_edit.setErrorEnabled(false)
        }

        if (edit_write_a_letter.getText().toString().length == 0) {
            edit_write_a_letter_edit.setError("Поле не должно быть пустым")
            valid = false
        }else{
            edit_write_a_letter_edit.setErrorEnabled(false)
        }

        return valid
    }
}
