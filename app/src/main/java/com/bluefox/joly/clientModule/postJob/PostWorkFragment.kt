package com.bluefox.joly.clientModule.postJob

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.HomeTitleUpdater
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.clientModule.postJob.supportFunctions.PostWorkUI
import com.bluefox.joly.clientModule.postJob.supportFunctions.SubmittedDialog
import com.bluefox.joly.clientModule.viewJob.ViewWorksFragment
import com.bluefox.joly.databinding.FragmentPostWorkBinding
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class PostWorkFragment : Fragment() {

    private lateinit var binding: FragmentPostWorkBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel
    private lateinit var loginViewModel : LoginViewModel


    private lateinit var postWorkUI: PostWorkUI

    private lateinit var submittedDialog: SubmittedDialog


    private var titleUpdater: HomeTitleUpdater? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeTitleUpdater) {
            titleUpdater = context
        } else {
            throw RuntimeException("$context must implement TitleUpdater")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_work, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        onClickListeners()

    }

    private fun onClickListeners() {
        binding.photo1.setOnClickListener {
            requestCameraAndStoragePermissions()
        }

        binding.photo2.setOnClickListener {
            requestCameraAndStoragePermissions()
        }

        binding.photo3.setOnClickListener {
            requestCameraAndStoragePermissions()
        }

        binding.photo4.setOnClickListener {
            requestCameraAndStoragePermissions()
        }

        binding.delPhoto1.setOnClickListener {
            deletePhoto(0)
        }

        binding.delPhoto2.setOnClickListener {
            deletePhoto(1)
        }

        binding.delPhoto3.setOnClickListener {
            deletePhoto(2)
        }

        binding.delPhoto4.setOnClickListener {
            deletePhoto(3)
        }
    }

    private fun initViews() {
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]


        postWorkUI = PostWorkUI(requireContext(), binding, ::postWorkDetails)
        sSapiFunctions = SSapiFunctions(
            requireContext(),
            viewLifecycleOwner,
            ssViewModel,
            ::categoriesListResponse,
            ::jobsListResponse,
            ::onWorkSubmitted,
            onGetWorksResponse = {}
        )

        submittedDialog = SubmittedDialog(layoutInflater,requireContext())

        callApis()
    }

    private fun callApis() {

        if(ServicesCatJob.isDataFetched)
        {
            postWorkUI.initCategoriesSpinner(ServicesCatJob.categoriesList)
            postWorkUI.initJobsSpinner(ServicesCatJob.jobList)
        }else{
            postWorkUI.showPB()
            sSapiFunctions.getCategories()
        }

    }

    private fun jobsListResponse(jobsList: List<JobItem>) {
        ServicesCatJob.isDataFetched = true

        postWorkUI.initJobsSpinner(jobsList)
        postWorkUI.hidePB()
    }

    private fun categoriesListResponse(categoriesList: List<CategoryItem>) {
        postWorkUI.initCategoriesSpinner(categoriesList)

        //Calling Jobs API
        sSapiFunctions.getJobs()
    }


    private fun postWorkDetails(postWorkData: PostWorkData) {
//        SSSelectedData.imagePart=getImageFromByteArrayNew()
//        SSSelectedData.parts.add(getImageFromByteArrayNew())
        if(isPhoto1Set) {
            loginViewModel.setCurrentFragment(0)
            submittedDialog.showLoading()
            sSapiFunctions.postWork(postWorkData)
        }else{
            UtilFunctions.showToast(requireContext(),"Select Image")
        }
    }

    private fun onWorkSubmitted()
    {
        SSSelectedData.reset()
        loginViewModel.setCurrentFragment(1)
        navigateToViewMyWorks()
        submittedDialog.showSubmitted()
//        UtilFunctions.showToast(requireContext(),"Work Submitted Successfully")
    }

    private fun navigateToViewMyWorks() {
        titleUpdater?.updateTitle("Posted Works")

        parentFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, ViewWorksFragment())
            .commit()
    }

    //////////////////////////Select Photos Logic///////////

    var isPhoto1Set = false
    var isPhoto2Set = false
    var isPhoto3Set = false
    var isPhoto4Set = false

    lateinit var photo1Uri: Uri
    lateinit var photo2Uri: Uri
    lateinit var photo3Uri: Uri
    lateinit var photo4Uri: Uri


    fun onImageSet(imagePart: MultipartBody.Part) {
        if (!isPhoto1Set) {
            binding.photo1.setImageURI(imageUri)
//            photo1Uri = uri
            isPhoto1Set = true

//            FilledWorkData.boardPhotosUris += photo1Uri
//            FilledWorkData.boardPhotosPaths += photo1Uri.path!!

            SSSelectedData.parts += imagePart
            SSSelectedData.workPhotosUris += imageUri

            binding.delPhoto1.visibility = View.VISIBLE
        } else if (!isPhoto2Set) {
            binding.photo2.setImageURI(imageUri)
//            photo2Uri = uri
            isPhoto2Set = true

//            FilledWorkData.boardPhotosUris += photo2Uri
//            FilledWorkData.boardPhotosPaths += photo2Uri.path!!

            SSSelectedData.parts += imagePart
            SSSelectedData.workPhotosUris += imageUri

            binding.delPhoto2.visibility = View.VISIBLE
        } else if (!isPhoto3Set) {
            binding.photo3.setImageURI(imageUri)
//            photo3Uri = uri
            isPhoto3Set = true

//            FilledWorkData.boardPhotosUris += photo3Uri
//            FilledWorkData.boardPhotosPaths += photo3Uri.path!!

            SSSelectedData.parts += imagePart
            SSSelectedData.workPhotosUris += imageUri

            binding.delPhoto3.visibility = View.VISIBLE
        } else {
            binding.photo4.setImageURI(imageUri)
//            photo4Uri = uri
            isPhoto4Set = true

//            FilledWorkData.boardPhotosUris += photo4Uri
//            FilledWorkData.boardPhotosPaths += photo4Uri.path!!

            SSSelectedData.parts += imagePart
            SSSelectedData.workPhotosUris += imageUri

            binding.delPhoto4.visibility = View.VISIBLE
        }
    }

    fun showErrorMessage(msg: String) {
//        AppUtils.showToast(mContext, msg)
    }

    private fun deletePhoto(position: Int) {
        SSSelectedData.workPhotosUris.removeAt(position)
        SSSelectedData.parts.removeAt(position)

        val maxIterations = 4

        for (index in 0 until maxIterations) {
            if (index < SSSelectedData.workPhotosUris.size) {
                // There is an item at this index, call setPhoto
                val photoItem = SSSelectedData.workPhotosUris[index]

                when (index) {
                    0 -> setPhoto(binding.photo1,binding.delPhoto1,photoItem)
                    1 -> setPhoto(binding.photo2,binding.delPhoto2,photoItem)
                    2 -> setPhoto(binding.photo3,binding.delPhoto3,photoItem)
                    3 -> setPhoto(binding.photo4,binding.delPhoto4,photoItem)
                }
            } else {
                // No item at this index, call clearPhoto
                when (index) {
                    0 -> clearPhoto(binding.photo1, binding.delPhoto1,0)
                    1 -> clearPhoto(binding.photo2, binding.delPhoto2,1)
                    2 -> clearPhoto(binding.photo3, binding.delPhoto3,2)
                    3 -> clearPhoto(binding.photo4, binding.delPhoto4,3)
                }
            }
        }


    }

    private fun setPhoto(photoView: ImageView, delPhoto: ImageView, uri: Uri)
    {
        photoView.setImageURI(uri)
        delPhoto.visibility=View.VISIBLE
    }

    private fun clearPhoto(photoView: ImageView, delPhoto: ImageView, position: Int)
    {
        photoView.setImageURI(null)
        delPhoto.visibility=View.GONE

        when (position) {
            0 -> isPhoto1Set=false
            1 -> isPhoto2Set=false
            2 -> isPhoto3Set=false
            3 -> isPhoto4Set=false
        }
    }


    //////////////////////////Pick Image///////////////////

    private val CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE = 100

    private val PICK_IMAGE_REQUEST = 1

    private val CAMERA_PICK_IMAGE_REQUEST = 2


    lateinit var imageUri: Uri
    var imagePath = ""

    var imageSelectedFalg = false

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
            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest,
                CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            intentChooser()
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

                intentChooser()

            } else {
                UtilFunctions.showToast(requireContext(), "Permission not granted")
                // Handle the case where permissions are not granted
            }
        }
    }

    private fun intentChooser() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val chooser = Intent.createChooser(galleryIntent, "Pick Image")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(chooser, PICK_IMAGE_REQUEST)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {


            // Check if the data is null or not
            if (data.data != null) {
                // Gallery intent
                val selectedImageUri: Uri = data.data!!
                // Handle the selected image from the gallery
                imageSelectedFalg = true
                handleGalleryImage(selectedImageUri)
            } else {
                // Camera intent
                imageSelectedFalg = true
                val imageBitmap = data.extras?.get("data") as Bitmap?
                // Handle the captured image from the camera
                handleCameraImage(imageBitmap)
            }


        }
    }

    var isGalleryPick = false

    private fun handleGalleryImage(selectedImageUri: Uri) {
        // Handle the selected image from the gallery
        isGalleryPick = true

        imageUri = selectedImageUri
        imagePath = getRealPathFromUri(selectedImageUri)

        onImageSet(getImageFromByteArrayNew(imageUri, imagePath))

    }

    private var imagePart: MultipartBody.Part? = null

    private fun handleCameraImage(imageBitmap: Bitmap?) {
        // Handle the captured image from the camera
        isGalleryPick = false

//        binding.imageView.setImageBitmap(imageBitmap)
//        binding.imageView.visibility = View.VISIBLE


        imageBitmap?.let { it ->
            val imageFile = createImageFileFromBitmap(it)
            imagePart = imageFile?.let { createImageMultipart(it) }

            UtilFunctions.showToast(requireContext(), "Mtp1")


            if (imagePart != null) {
                SSSelectedData.imagePart=imagePart
                UtilFunctions.showToast(requireContext(), "Mtp")
//                postImage(imagePart)
            }
        }
    }

    private fun createImageFileFromBitmap(bitmap: Bitmap): File? {
        val file = File(requireContext().externalCacheDir, "${UtilFunctions.getCurrentDateTimeSeconds()}.jpg")
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun createImageMultipart(file: File): MultipartBody.Part {
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("Photos[]", file.name, requestFile)
    }

    private fun getRealPathFromUri(uri: Uri): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(projection[0])
        val picturePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return picturePath ?: ""
    }

    private fun getImageFromByteArrayNew(imageUri: Uri,imagePath: String): MultipartBody.Part {
        var bmp: Bitmap? = null
        try {
            bmp = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
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

        return MultipartBody.Part.createFormData("Photos[]", file.name, requestBody)
    }



}