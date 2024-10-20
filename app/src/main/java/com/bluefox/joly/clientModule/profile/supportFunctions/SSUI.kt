package com.bluefox.joly.clientModule.profile.supportFunctions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.profile.modalClass.SSProfileDetailsData
import com.bluefox.joly.databinding.FragmentProfileBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class SSUI(
    val context: Context,
    val activity: Activity,
    private val binding: FragmentProfileBinding,
    private val onSubmitClicked: (ssRegistrationDetailsData: SSRegistrationDetailsData) -> Unit,
    private val logoutClicked: () -> Unit
) {

    init {
        disableAllEditTexts()
        setDetails()
        genderOnClickListener()
        onClickListeners()
    }

    fun onClickListeners() {

        binding.ltServiceSeeker.ivEdit.setOnClickListener {
            enableAllEditTexts()
        }

        binding.ltServiceSeeker.ivLogout.setOnClickListener {
            logoutClicked.invoke()
        }

        binding.ltServiceSeeker.btSubmit.setOnClickListener {
            getValues()
        }

        binding.ltServiceSeeker.profilePic.setOnClickListener {
            CallIntent.gotoProfileImageActivity(context, false, activity)
        }

    }

    private fun setDetails() {
        val profileData = SSProfileData.mLoginData

        binding.ltServiceSeeker.apply {
            tvMobileNumber.text = profileData.phoneNumber
            etName.setText(profileData.name)
            etAadharNumber.setText(profileData.aadharNumber)
            etDOB.setText(profileData.age)
            etLocation.setText(profileData.location)
            etPincode.setText(profileData.pincode)
            etAddress.setText(profileData.address)

            if(profileData.alternativeNumber=="E")
                etAlternativeNumber.setText("No Number")
            else
                etAlternativeNumber.setText(profileData.alternativeNumber)

            val genderValue = SSProfileData.mLoginData.gender?.toInt()

            when (genderValue) {
                1 -> rbFemale.isChecked = true
                2 -> rbMale.isChecked = true
                0 -> rbOther.isChecked = true
            }
        }
    }

    private fun getValues() {
        binding.ltServiceSeeker.apply {
            val ssRegistrationDetailsData = SSRegistrationDetailsData()

            val nName = etName.text.toString()
            if (isEditTextEmpty(etName, context, "Enter Name")) return
            ssRegistrationDetailsData.name = nName

            val nAadharNumber = etAadharNumber.text.toString()
            if (isEditTextEmpty(etAadharNumber, context, "Enter Aadhar Number")) return
            ssRegistrationDetailsData.aadharNumber = nAadharNumber

            val nDOB = etDOB.text.toString()
            if (isEditTextEmpty(etDOB, context, "Enter Date of Birth")) return
            ssRegistrationDetailsData.dateOfBirth = nDOB

            if (nGenderSelected == -1) {
                UtilFunctions.showToast(context, "Select Gender")
                return
            }

            val nLocation = etLocation.text.toString()
            if (isEditTextEmpty(etLocation, context, "Enter Location")) return
            ssRegistrationDetailsData.location = nLocation

            val nPincode = etPincode.text.toString()
            if (isEditTextEmpty(etPincode, context, "Enter Pincode")) return
            ssRegistrationDetailsData.pinCode = nPincode

            val nAddress = etAddress.text.toString()
            if (isEditTextEmpty(etAddress, context, "Enter Address")) return
            ssRegistrationDetailsData.address = nAddress

            // TODO: Callback
        }
    }

    private fun isEditTextEmpty(editText: EditText, context: Context, message: String): Boolean {
        return if (editText.text.toString().trim().isEmpty()) {
            UtilFunctions.showToast(context, message)
            true
        } else {
            false
        }
    }

    private fun disableAllEditTexts() {
        binding.ltServiceSeeker.apply {
            disableET(etName)
            disableET(etAlternativeNumber)
            disableET(etAadharNumber)
            disableET(etDOB)
            disableET(etLocation)
            disableET(etPincode)
            disableET(etAddress)
            rgGender.isEnabled = false
            rbMale.isEnabled = false
            rbFemale.isEnabled = false
            rbOther.isEnabled = false
            btSubmit.visibility = View.GONE
        }
    }

    private fun enableAllEditTexts() {
        binding.ltServiceSeeker.apply {
            enableET(etName)
            enableET(etAadharNumber)
            enableET(etDOB)
            enableET(etLocation)
            enableET(etPincode)
            enableET(etAddress)
            rgGender.isEnabled = true
            rbMale.isEnabled = true
            rbFemale.isEnabled = true
            rbOther.isEnabled = true
            btSubmit.visibility = View.VISIBLE
        }
    }

    private fun disableET(view: View) {
        view.isClickable = false
        view.isEnabled = false
    }

    private fun enableET(view: View) {
        view.isClickable = true
        view.isEnabled = true
    }

    private var nGenderSelected = -1
    private fun genderOnClickListener() {
        binding.ltServiceSeeker.rgGender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbFemale -> {
                    nGenderSelected = 1
                }
                R.id.rbMale -> {
                    nGenderSelected = 2
                }
                R.id.rbOther -> {
                    nGenderSelected = 0
                }
            }
        }
    }
}
