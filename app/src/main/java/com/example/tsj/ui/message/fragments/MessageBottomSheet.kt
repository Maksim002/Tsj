package com.example.tsj.ui.message.fragments


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.model.ReplyModel
import com.example.tsj.ui.message.MessagesViewModel
import com.example.tsj.utils.MyUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_message_bottom_sheet.*
import kotlinx.android.synthetic.main.new_message_chairman.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MessageBottomSheet(private val idMessage: Int) : BottomSheetDialogFragment() {
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private lateinit var viewModel: MessagesViewModel
    private lateinit var reply: ReplyModel

    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)

        send_msg_imageiew.setOnClickListener {

            MyUtils.hideKeyboard(activity!!,view)

            val title = edit_title.text.toString()
            val body = edit_sms.text.toString()
            //проверка на пустоту edit text
            if (validateEditText(title, body)) {
                edit_title_text.error = null
                edit_sms_text.error = null

            }else{
                edit_title_text.error = "Загаловка не может быть пустым"
                edit_sms_text.error = "Письмо не может быть пустым"
            }

            if (reply.isToManager) {
                viewModel.sendMessageToManager(edit_title.text.toString(), edit_sms.text.toString(), files).observe(this, Observer {
                    if (it) {
                        dismiss()
                        findNavController().popBackStack()
                    }
                })
            } else {
                viewModel.messageToPerson(
                    reply.personId,
                    edit_title.text.toString(),
                    edit_sms.text.toString(),
                    files
                ).observe(this,
                    Observer {
                        if (it) {
                            dismiss()
                            findNavController().popBackStack()
                        }
                    })
                Toast.makeText(context, "Неудочно", Toast.LENGTH_LONG).show()
            }
        }


        fasten_file_imageview.setOnClickListener {
            MyUtils.hideKeyboard(activity!!, view)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        context!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    requestPermissions(permissions, STORAGE_PERMISION_CODE)

                } else {
                    getMyFile()
                }
            } else {
                getMyFile()
            }
        }
    }

    private fun validateEditText(title: String, content: String): Boolean {
        return title.isNotEmpty() || content.isNotEmpty()
    }

    private fun getMyFile() {
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.setType("*/*")
        startActivityForResult(myFile, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getMyFile()
        } else {
            //permission from popup denied
            Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)

                files.add(photo)
                names.add(photo.toString().substring(0, 15))
            }
        }
    }

    private fun getPath(uri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(context!!, uri, proj, null, null, null)
        val cursor = loader.loadInBackground()
        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val res = cursor.getString(columnIndex)
        cursor.close()
        return res

    }

    override fun onStart() {
        super.onStart()

        viewModel.reply(idMessage).observe(this, Observer {
            reply = it
        })
    }
}
