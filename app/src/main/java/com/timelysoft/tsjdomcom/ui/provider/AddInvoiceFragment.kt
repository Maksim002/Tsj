package com.timelysoft.tsjdomcom.ui.provider

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.AddInvoiceAdapter
import com.timelysoft.tsjdomcom.adapters.provider.AddInvoiceListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.FileModel
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoices
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoicesIdModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import kotlinx.android.synthetic.main.item_add_invoice.*
import kotlinx.android.synthetic.main.item_add_invoice.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class AddInvoiceFragment : Fragment(), AddInvoiceListener {
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()
    private val myAdapter = AddInvoiceAdapter(this)
    private var viewModel = ProviderViewModel()
    private var position: Int = 0
    private var model = ProviderInvoicesIdModel()
    private var list: ArrayList<FileModel> = arrayListOf()
    private var mLastClickTime: Long = 0
    private var providerId: Int = 0
    private lateinit var dataTo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_invoice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
        check()
        initRecycler()
        getInvoiceProvider()
        getInvoiceAtDate()
    }

    companion object {
        var myImage: HashMap<String, Bitmap> = hashMapOf()
    }

    private fun initArgument() {
        add_invoice_add_file.setOnClickListener {
            loadFiles()
        }

        position = try {
            arguments!!.getInt("SupplierAccounts")
        } catch (e: Exception) {
            -1
        }

        val supplierAccountsId = try {
            arguments!!.getInt("SupplierAccountsId")
        } catch (e: Exception) {
            0
        }

        add_invoice_save.setOnClickListener {
            viewModel.addInvoice(
                add_invoice_service_out.text.toString(),
                providerId,
                dataTo,
                add_invoice_meter_reading_out.text.toString(),
                add_invoice_for_payment_out.text.toString(),
                files
            ).observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                when (result.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }


        if (position != -1) {
            MainActivity.alert.show()
            viewModel.providerInvoicesId(supplierAccountsId)
                .observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    val data = result.data
                    when (result.status) {
                        Status.SUCCESS -> {

                            add_invoice_provider_out.setText(data!!.providerName)
                            add_invoice_service_out.setText(data.service)
                            add_invoice_at_date_out.setText(data.date)
                            add_invoice_meter_reading_out.setText(data.countersValue.toString())
                            add_invoice_for_payment_out.setText(data.paymentAmount.toString())

                            model.id = data.id
                            model.providerId = data.providerId
                            model.providerName = data.providerName
                            model.paymentAmount = data.paymentAmount
                            model.countersValue = data.countersValue
                            model.date = data.date
                            model.service = data.service
                            model.files = data.files

                            list = model.files as ArrayList<FileModel>

                            myAdapter.update(data.files as ArrayList<FileModel>)
                            MainActivity.alert.hide()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })

            add_invoice_save.setOnClickListener {
                MainActivity.alert.show()
                files.clear()
                myImage.forEach {
                    files.add(buildImageBodyPart(it.key, it.value))
                }
                viewModel.providerInvoicesEdit(
                    model.id,
                    model.service,
                    model.providerId,
                    model.date,
                    model.countersValue,
                    model.paymentAmount,
                    files
                ).observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    when (result.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                            MainActivity.alert.hide()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    override fun clearClickListener(position: Int, item: FileModel) {
        myImage.remove(item.name)
        myAdapter.items.removeAt(position)
    }

    private fun initRecycler() {
        add_invoice_recycler.adapter = myAdapter
    }

    private fun loadFiles() {
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

    private fun getMyFile() {
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.setType("*/*");
        startActivityForResult(Intent.createChooser(myFile, "Select Picture"), IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getMyFile()
        } else {
            Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("image", file.name, requestFile)
                files.add(photo)
                names.add(file.name)

                // todo Нужно добовлять фаел
                if (position != -1) {
                    list.add(FileModel(file.name, uri.toString()))
                    myAdapter.update(list)
                    myAdapter.notifyDataSetChanged()
                } else {
                    list.add(FileModel(file.name, uri.toString()))
                    myAdapter.update(list)
                    myAdapter.notifyDataSetChanged()
                }
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

    private fun check() {
        if (position != -1) {
            (activity as AppCompatActivity).supportActionBar?.title = "Редактировать"
        }
    }

    private fun buildImageBodyPart(fileName: String, bitmap: Bitmap): MultipartBody.Part {
        val leftImageFile = convertBitmapToFile(fileName, bitmap)
        val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), leftImageFile)
        return MultipartBody.Part.createFormData(fileName, leftImageFile.name, reqFile)
    }

    //convert bitmap to MultipartBody.Part
    private fun convertBitmapToFile(fileName: String, bitmap: Bitmap): File {
        //create a file to write bitmap data
        val file = File(context!!.cacheDir, fileName)
        file.createNewFile()
        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(
            Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos
        )
        val bitMapData = bos.toByteArray()
        //write the bytes in file
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file
    }

    private fun getInvoiceProvider() {
        var list: ArrayList<ProviderInvoices> = arrayListOf()

        viewModel.providerInvoices()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterInvoiceProvider = data?.let {
                            ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                        }
                        add_invoice_provider_out.setAdapter(adapterInvoiceProvider)

                        list = data as ArrayList<ProviderInvoices>
                    }
                    Status.NETWORK, Status.ERROR -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })

        add_invoice_provider_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_invoice_provider_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_invoice_provider_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_invoice_provider_out.clearFocus()
                providerId = list[position].id
            }
        add_invoice_provider_out.setOnClickListener {
            add_invoice_provider_out.showDropDown()
        }
        add_invoice_provider_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        add_invoice_provider_out.showDropDown()
                    }
                    if (!hasFocus && add_invoice_provider_out.text!!.isNotEmpty()) {
                        add_invoice_provider.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        add_invoice_provider.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        add_invoice_provider_out.clearFocus()
    }

    private fun getInvoiceAtDate() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        add_invoice_at_date_out.keyListener = null;
        add_invoice_at_date_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                add_invoice_at_date.defaultHintTextColor = col

                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        add_invoice_at_date_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dataTo = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    }, year, month, day
                )
                picker.show()
                add_invoice_at_date_out.clearFocus()
            }
        }
    }
}