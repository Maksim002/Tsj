package com.timelysoft.tsjdomcom.ui.provider

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.AddInvoiceAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.FileModel
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoicesIdModel
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception

class AddInvoiceFragment : Fragment() {
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()
    private val myAdapter = AddInvoiceAdapter()
    private var viewModel = ProviderViewModel()
    private var position: Int = 0
    private var model = ProviderInvoicesIdModel()
    private var list: ArrayList<FileModel> = arrayListOf()

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

        if (position != -1) {
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

                            list = model.files as  ArrayList<FileModel>


                            myAdapter.update(data.files as ArrayList<FileModel>)
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

        add_invoice_save.setOnClickListener {

            viewModel.providerInvoicesEdit(model.id, model.service, model.providerId, model.date, model.countersValue, model.paymentAmount, files
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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
                val requestFile = file.asRequestBody("file/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
                files.add(photo)
                names.add(photo.toString())

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
}