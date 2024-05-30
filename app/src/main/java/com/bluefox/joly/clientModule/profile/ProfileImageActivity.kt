package com.bluefox.joly.clientModule.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.databinding.ActivityProfileImageBinding
import com.bluefox.joly.zCommonFunctions.ImageCropperHandler
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileImageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileImageBinding

    //Image Picker
    private val imageCropperHandler = ImageCropperHandler(
        this,
        registerForActivityResult(CropImageContract()) { /* handle result */
                result ->
            when {
                result.isSuccessful -> {
                    result.uriContent?.let {
                        Log.e("Test", "One")

                        binding.ivProfilePhoto.setImageURI(it)
                    }
                }

                result is CropImage.CancelledResult -> Log.e("Test","cropping image was cancelled by the user")//addWorkSheet.showErrorMessage("cropping image was cancelled by the user")
                else -> Log.e("Test","cropping image failed") //addWorkSheet.showErrorMessage("cropping image failed")
            }
        },
        registerForActivityResult(CropImageContract()) { /* handle result */
            if (it !is CropImage.CancelledResult) {
                it.uriContent?.let { it1 ->
                    binding.ivProfilePhoto.setImageURI(it1)

                    binding.btUpdatePhoto.text="Save Photo"

                    Log.e("Test", "Two")

                }
            }
        },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        Glide.with(this)
            .load(SSProfileData.mLoginData.photo)
            .fitCenter()
            .into(binding.ivProfilePhoto)

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btUpdatePhoto.setOnClickListener {
            val btText = binding.btUpdatePhoto.text

            if(btText.equals("Save Photo"))
            {
//                Call Update Photo
            }else{
                imageCropperHandler.startCameraWithoutUri(includeCamera = false, includeGallery = true)
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}