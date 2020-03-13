package com.example.tsj.ui.message.send


import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.R
import com.example.tsj.ui.message.MessagesViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.new_message_chairman.*
import okhttp3.MultipartBody
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import java.io.File
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class NewMessageManager : Fragment() {
    private lateinit var viewModel: MessagesViewModel
    private lateinit var editBody: TextInputEditText
    private lateinit var editTitle: TextInputEditText
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()

    private lateinit var textGone: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.new_message_chairman, container, false)

        editBody = root.findViewById(R.id.manager_msg_referenc)
        editTitle = root.findViewById(R.id.manager_msg_content)

        textGone = root.findViewById(R.id.textGone)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_message -> {
                sendMessage()
            }
            R.id.fasten_file -> {
                loadFiles()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFiles() {
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.setType("*/*")
        startActivityForResult(myFile, IMAGE_PICK_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
//                photos[position] = photo
                files.add(photo)
//                photoList[position] = getPath(data.data!!)
//                photosAdapter.setData(photoList)
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


    private fun validateEditText(title: String, content: String): Boolean {
        return title.isNotEmpty() || content.isNotEmpty()
    }

    private fun sendMessage() {

        val title = manager_msg_referenc.text.toString()
        val body = manager_msg_content.text.toString()
        //проверка на пустоту edit text
        if (validateEditText(title, body)) {
            title_container.error = null
            content_container.error = null
            //Метод для post сообщания
            viewModel.sendMessageToManager(body,title, files).observe(this, Observer {
                if (it){
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(context, "Неудочно", Toast.LENGTH_LONG).show()
                }
            })

        } else {
            title_container.error = "Загаловка не может быть пустым"
            content_container.error = "Письмо не может быть пустым"
        }

    }

    override fun onStart() {
        super.onStart()

    }
}
