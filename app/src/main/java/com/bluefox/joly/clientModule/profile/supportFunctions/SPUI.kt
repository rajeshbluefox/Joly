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


class SPUI(
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

        binding.ltServiceProvider.ivEdit.setOnClickListener {
            enableAllEditTexts()
        }

        binding.ltServiceProvider.ivLogout.setOnClickListener {
            logoutClicked.invoke()
        }

        binding.ltServiceProvider.btSubmit.setOnClickListener {
            getValues()
        }

        binding.ltServiceProvider.profilePic.setOnClickListener {
            CallIntent.gotoProfileImageActivity(context, false, activity)
        }

    }

    private fun setDetails() {
        val profileData = SSProfileData.mLoginData

        binding.ltServiceProvider.apply {
            tvMobileNumber.text = profileData.phoneNumber

            etCompany.setText(profileData.companyName)
            etAadharNumber.setText(profileData.aadharNumber)
            etDOB.setText(profileData.age)
            etCity.setText(profileData.city)
            etState.setText(profileData.state)
            etAddress.setText(profileData.address)
            etPincode.setText(profileData.pincode)
            etLocation.setText(profileData.location)
            etQualification.setText(profileData.qualification)
            etPreviousExperience.setText(profileData.previousExperience)
            etPortfolioLink.setText(profileData.portfolioLink)
            etDescription.setText(profileData.description)
            etWorkingHours.setText(profileData.workingHours)

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
        binding.ltServiceProvider.apply {
            val ssRegistrationDetailsData = SSRegistrationDetailsData()

            val nCompanyName = etCompany.text.toString()
            if (isEditTextEmpty(etCompany, context, "Enter CompanyName")) return
            ssRegistrationDetailsData.companyName = nCompanyName

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

            val nCity = etCity.text.toString()
            if (isEditTextEmpty(etCity, context, "Enter City")) return
            ssRegistrationDetailsData.city = nCity

            val nState = etState.text.toString()
            if (isEditTextEmpty(etState, context, "Enter State")) return
            ssRegistrationDetailsData.state = nState

            val nAddress = etAddress.text.toString()
            if (isEditTextEmpty(etAddress, context, "Enter Address")) return
            ssRegistrationDetailsData.address = nAddress

            val nPincode = etPincode.text.toString()
            if (isEditTextEmpty(etPincode, context, "Enter Pincode")) return
            ssRegistrationDetailsData.pinCode = nPincode

            val nLocation = etLocation.text.toString()
            if (isEditTextEmpty(etLocation, context, "Enter Location")) return
            ssRegistrationDetailsData.location = nLocation

            val nQualification = etQualification.text.toString()
            if (isEditTextEmpty(etQualification, context, "Enter Qualification")) return
            ssRegistrationDetailsData.qualification = nQualification

            val nPreviousExperience = etPreviousExperience.text.toString()
            if (isEditTextEmpty(etPreviousExperience, context, "Enter Previous Experience")) return
            ssRegistrationDetailsData.previousExperience = nPreviousExperience

            val nPortfolioLink = etPortfolioLink.text.toString()
            if (isEditTextEmpty(etPortfolioLink, context, "Enter Portfolio Link")) return
            ssRegistrationDetailsData.portfolioLink = nPortfolioLink

            val nDescription = etDescription.text.toString()
            if (isEditTextEmpty(etDescription, context, "Enter Description")) return
            ssRegistrationDetailsData.description = nDescription


            //TODO Callback
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
        binding.ltServiceProvider.apply {
            disableET(etCompany)
            disableET(etAlternativeNumber)
            disableET(etAadharNumber)
            disableET(etDOB)
            disableET(etCity)
            disableET(etState)
            disableET(etAddress)
            disableET(etPincode)
            disableET(etLocation)
            disableET(etQualification)
            disableET(etPreviousExperience)
            disableET(etPortfolioLink)
            disableET(etDescription)
            disableET(etWorkingHours)

            rgGender.isEnabled = false
            rbMale.isEnabled = false
            rbFemale.isEnabled = false
            rbOther.isEnabled = false
            btSubmit.visibility = View.GONE
        }
    }

    private fun enableAllEditTexts() {
        binding.ltServiceProvider.apply {
            enableET(etCompany)
            enableET(etAadharNumber)
            enableET(etDOB)
            enableET(etCity)
            enableET(etState)
            enableET(etAddress)
            enableET(etPincode)
            enableET(etLocation)
            enableET(etQualification)
            enableET(etPreviousExperience)
            enableET(etPortfolioLink)
            enableET(etDescription)
            rgGender.isEnabled = true
            rbMale.isEnabled = true
            rbFemale.isEnabled = true
            rbOther.isEnabled = true
            btSubmit.visibility = View.VISIBLE
        }
    }


    private fun disableET(view: View) {
        view.isClickable = false;
        view.isEnabled = false
    }

    private fun enableET(view: View) {
        view.isClickable = true;
        view.isEnabled = true;
    }

    private var nGenderSelected = -1
    private fun genderOnClickListener() {
        binding.ltServiceProvider.rgGender.setOnCheckedChangeListener { group, checkedId ->
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
