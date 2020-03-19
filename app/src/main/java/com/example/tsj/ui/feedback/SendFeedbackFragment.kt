package com.example.tsj.ui.feedback


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tsj.R
import com.example.tsj.utils.MyUtils
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
        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.send_feedback -> {
                senFeedBack()
            }
        }
        return super.onOptionsItemSelected(item)

    }   

    private fun senFeedBack() {
        MyUtils.hideKeyboard(activity!!, view!!)
        if (edit_to_whom.text.length == 0 || edit_your_mail.text.length == 0 || edit_write_a_letter.text.length == 0){
            Toast.makeText(context, "Заполните все поля", Toast.LENGTH_LONG).show()
        }else{

        }
    }
}
