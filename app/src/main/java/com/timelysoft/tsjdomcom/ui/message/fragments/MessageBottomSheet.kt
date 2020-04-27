package com.timelysoft.tsjdomcom.ui.message.fragments


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.model.ReplyModel
import com.timelysoft.tsjdomcom.ui.message.MessagesViewModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.timelysoft.tsjdomcom.service.Status
import kotlinx.android.synthetic.main.fragment_message_bottom_sheet.*
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
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_message_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        edit_title_text
        edit_title.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && edit_title.text!!.isNotEmpty()) {
                edit_title_sheet_message.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                edit_title_sheet_message.isErrorEnabled = false
            }
        }

        edit_sms.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && edit_sms.text!!.isNotEmpty()) {
                edit_sms_sheet_message.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                edit_sms_sheet_message.isErrorEnabled = false
            }
        }

        send_image_sheet_message.setOnClickListener {
            MyUtils.hideKeyboard(activity!!, view)
            if (validate()) {
                if (reply.isToManager) {
                    MainActivity.alert.show()

                    viewModel.sendMessageToManagerN(edit_sms.text.toString(), edit_title.text.toString(), files).observe(viewLifecycleOwner, Observer { result ->
                        val msg = result.msg
                        when(result.status){
                            Status.SUCCESS ->{
                                MainActivity.alert.hide()
                                    dismiss()
                                    findNavController().popBackStack()
                            }
                            Status.ERROR, Status.NETWORK ->{
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                } else {
                    MainActivity.alert.show()
                    viewModel.messageToPerson(
                        reply.personId,
                        edit_sms.text.toString(),
                        edit_title.text.toString(),
                        files
                    ).observe(viewLifecycleOwner,
                        Observer {
                            MainActivity.alert.hide()
                            if (it) {
                                dismiss()
                                Toast.makeText(
                                    context,
                                    "Ваше сообщение отправлено!",
                                    Toast.LENGTH_LONG
                                ).show()
                                findNavController().popBackStack()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Ошибка при отпровлении данных",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })

                }
            }

            fasten_file_image_sheet_message.setOnClickListener {
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
    }

    private fun validate(): Boolean {
        var valid = true
        if (edit_title.text.toString().isEmpty()) {
            edit_title_sheet_message.error = "Заголовок не должен быть пустым"
            valid = false
        } else {
            edit_title_sheet_message.isErrorEnabled = false
        }

        if (edit_sms.text.toString().isEmpty()) {
            edit_sms_sheet_message.error = "Письмо не должно быть пустым"
            valid = false
        } else {
            edit_sms_sheet_message.isErrorEnabled = false
        }

        return valid
    }


    private fun getMyFile() {
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.type = "*/*"
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
