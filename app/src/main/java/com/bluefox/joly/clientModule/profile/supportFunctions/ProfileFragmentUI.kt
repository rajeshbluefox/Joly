package com.bluefox.joly.clientModule.profile.supportFunctions

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.FragmentProfileBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bumptech.glide.Glide


class ProfileFragmentUI(
    val context: Context,
    private val binding: FragmentProfileBinding
) {

    init {

        setData()
        stopEditing()
        onClickListeners()
    }

    private fun setData()
    {
        binding.tvMobileNumber.text=SSProfileData.mLoginData.phoneNumber
        binding.etName.setText(SSProfileData.mLoginData.name)
        binding.etAadharNum.setText(SSProfileData.mLoginData.aadharNumber)
        binding.etDOB.setText(SSProfileData.mLoginData.age)
        binding.etGender.setText(SSProfileData.mLoginData.gender)
        binding.etPinCode.setText(SSProfileData.mLoginData.pincode)
        binding.etAddress.setText(SSProfileData.mLoginData.address)

//        Glide.with(context)
//            .load(SSProfileData.mLoginData.photo)
//            .fitCenter()
//            .into(binding.profilePic)
    }

    private fun onClickListeners() {
        binding.ivEdit.setOnClickListener {
            startEditing()
        }

        binding.btSubmit.setOnClickListener {

            stopEditing()
        }
    }

    private fun stopEditing() {
        binding.ivEdit.visibility = View.VISIBLE
        binding.btSubmit.visibility = View.GONE

        disableET(binding.ltName)
        disableET(binding.ltAadharNum)
        disableET(binding.ltDOB)
        disableET(binding.ltGender)
        disableET(binding.ltPinCode)
        disableET(binding.ltAddress)

    }

    private fun startEditing() {
        binding.ivEdit.visibility = View.GONE
        binding.btSubmit.visibility = View.VISIBLE

        enableET(binding.ltName)
        enableET(binding.ltAadharNum)
        enableET(binding.ltDOB)
        enableET(binding.ltGender)
        enableET(binding.ltPinCode)
        enableET(binding.ltAddress)
    }

    private fun disableET(view: View) {
//        view.isFocusable = false;
        view.isClickable = false;
        view.isEnabled = false
    }

    private fun enableET(view: View) {
//        view.isFocusable = true;
        view.isClickable = true;
        view.isEnabled = true;
    }

    fun setBottomMargin(view: View,margin : Int) {
        // Assuming tabLayout is your TabLayout instance
        val params = view.layoutParams as ConstraintLayout.LayoutParams

        params.topMargin = margin
        view.layoutParams = params
    }




}
