package com.bluefox.joly.clientModule.login

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.apiFunctions.LoginAPIFunctions
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.login.supportFunctions.RegisterActivityUI
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.postJob.supportFunctions.TermsDialog
import com.bluefox.joly.databinding.ActivityRegisterBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.ImageCropperHandler
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerActivityUI: RegisterActivityUI

    private lateinit var loginViewModel : LoginViewModel

    private lateinit var loginAPIFunctions: LoginAPIFunctions

    private var isImageSelected = false

    private lateinit var termsDialog: TermsDialog

    //Image Picker
    private val imageCropperHandler = ImageCropperHandler(
        this,
        registerForActivityResult(CropImageContract()) { /* handle result */
                result ->
            when {
                result.isSuccessful -> {
                    result.uriContent?.let {
                        Log.e("Test", "One")
                        isImageSelected=true
                        binding.profilePic.setImageURI(it)
                    }
                }

                result is CropImage.CancelledResult -> Log.e("Test","cropping image was cancelled by the user")//addWorkSheet.showErrorMessage("cropping image was cancelled by the user")
                else -> Log.e("Test","cropping image failed") //addWorkSheet.showErrorMessage("cropping image failed")
            }
        },
        registerForActivityResult(CropImageContract()) { /* handle result */
            if (it !is CropImage.CancelledResult) {
                it.uriContent?.let { it1 ->
                    binding.profilePic.setImageURI(it1)
                    isImageSelected=true


//                    imageUri = it1
//                    imagePath = getRealPathFromUri(it1)
//
//                    SSSelectedData.registerPhoto=getImageFromByteArrayNew(imageUri, imagePath)
//                    binding.btUpdatePhoto.text="Save Photo"
                    SSSelectedData.registerPhoto=createMultipartBodyPart(it1, this)


                    Log.e("Test", "Two")

                }
            }
        },
    )

    fun createMultipartBodyPart(uri: Uri, context: Context): MultipartBody.Part {
        val file = uriToFile(uri, context)
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("Photo", file.name, requestFile)
    }

    private fun uriToFile(uri: Uri, context: Context): File {
        val file = File(context.cacheDir, "temp_image_file.jpg")
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)


        initViews()
        control()
        onClickListeners()
    }

    private fun control()
    {
        when(UserDetails.getUserRoleStatus(this))
        {
            1 -> {
                binding.tvAppBarTitle.text="Service Seeker"
                binding.ltServiceProvider.visibility = View.GONE
            }
            2 -> {
                binding.tvAppBarTitle.text="Service Provider"
                binding.ltServiceProvider.visibility = View.VISIBLE
            }
            3 -> {
                binding.tvAppBarTitle.text="Job Provider"
            }
            4 -> {
                binding.tvAppBarTitle.text="Job Seeker"
            }
        }

    }

    private fun initViews() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        registerActivityUI = RegisterActivityUI(this, binding,::onSubmitClicked)
        loginAPIFunctions = LoginAPIFunctions(this,this,loginViewModel, onLoginResponse = {},::onRegisterResponse )

        termsDialog = TermsDialog(layoutInflater, this)

    }

    private fun onClickListeners() {
        binding.showSignIn.setOnClickListener {
            CallIntent.gotoLogin(this, true, this)
        }

        binding.etSelectDate.setOnClickListener {
            initDatePicker()
        }

        binding.profilePic.setOnClickListener {
            requestCameraAndStoragePermissions()
        }

        binding.btTNC.setOnClickListener {
            val useRole = UserDetails.getUserRoleStatus(this)
            termsDialog.openTNCDialog(useRole)
        }
    }

    private val cal = Calendar.getInstance()
    private var selectedDate = ""

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd/MM/yyyy" // mention the format you need
            val myFormat1 = "yyyy-MM-dd"
            val sdfCalc = SimpleDateFormat(myFormat1, Locale.US)

            val selectedDateForCalculation = sdfCalc.format(cal.time)

            val age = calculateAge(selectedDateForCalculation, myFormat1)

            binding.etDOB.setText(age.toString())

//            binding.etSelectDate.text = "${selectedDateForCalculation} , Age - $age"
            binding.etSelectDate.text = "${selectedDateForCalculation}"

            selectedDate = selectedDateForCalculation
//            createVendorUIFunctions.setDateSelected(true)

        }

    private fun calculateAge(dob: String, format: String): Int {
        val formatter = DateTimeFormatter.ofPattern(format, Locale.US)
        val birthDate = LocalDate.parse(dob, formatter)
        val currentDate = LocalDate.now()
        return Period.between(birthDate, currentDate).years
    }

    private fun initDatePicker() {

//        val minimumDate = convertStringToDate(minimumDateString)

        val calendar = Calendar.getInstance()
//        calendar.time = minimumDate

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePic = DatePickerDialog(
            this, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )


//        datePic.datePicker.minDate = minimumDate.time
        datePic.show()


    }


    private fun onSubmitClicked(sSRegistrationDetailsData : SSRegistrationDetailsData)
    {

        if(isImageSelected) {
            loginAPIFunctions.ssRegister(sSRegistrationDetailsData)
        }else{
            UtilFunctions.showToast(this,"Please select a photo")
        }

        Log.e("test","Name ${sSRegistrationDetailsData.name}")
    }

    private fun onRegisterResponse()
    {
        //Code after Login response is received
        // SAVE THE USER details to Shared Preference
        CallIntent.gotoLogin(this, true,this)

    }

    lateinit var imageUri: Uri
    var imagePath = ""

    private val CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE = 100

    private fun requestCameraAndStoragePermissions() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        } else {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }

        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest,
                CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            openandPickImage()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // Both permissions are granted

                openandPickImage()

            } else {
                UtilFunctions.showToast(this, "Permission not granted")
                // Handle the case where permissions are not granted
            }
        }
    }

    fun openandPickImage()
    {
        imageCropperHandler.startCameraWithoutUri(includeCamera = false, includeGallery = true)
    }

    private fun getImageFromByteArrayNew(imageUri: Uri, imagePath: String): MultipartBody.Part {
        var bmp: Bitmap? = null
        try {
            bmp = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val baos = ByteArrayOutputStream()
        bmp?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val originalSize = baos.size() ?: 0

        if (originalSize > 100 * 1024) { // Check if size is greater than 100 KB
            var quality = 100
            var compressedSize = originalSize

            while (compressedSize > 100 * 1024 && quality > 0) {
                baos.reset() // Reset the output stream
                bmp?.compress(Bitmap.CompressFormat.JPEG, quality, baos)
                compressedSize = baos.size() ?: 0
                quality -= 5 // Decrease the quality by 5 in each iteration
            }
        }

        val fileInBytes: ByteArray = baos.toByteArray()

        val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), fileInBytes)
        val file = File(imagePath)

        return MultipartBody.Part.createFormData("Photo", file.name, requestBody)
    }

    private fun getRealPathFromUri(uri: Uri): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = this.contentResolver.query(uri, projection, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(projection[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return picturePath ?: ""
    }


}