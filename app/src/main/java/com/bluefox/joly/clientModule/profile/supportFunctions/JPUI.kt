package com.bluefox.joly.clientModule.profile.supportFunctions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.databinding.FragmentProfileBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class JPUI(
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

        binding.ltJobProvider.ivEdit.setOnClickListener {
            enableAllEditTexts()
        }

        binding.ltJobProvider.ivLogout.setOnClickListener {
            logoutClicked.invoke()
        }

        binding.ltJobProvider.btSubmit.setOnClickListener {
            getValues()
        }

        binding.ltJobProvider.profilePic.setOnClickListener {
            CallIntent.gotoProfileImageActivity(context, false, activity)
        }

    }

    private fun setDetails() {
        val profileData = SSProfileData.mLoginData

        binding.ltJobProvider.apply {
            tvMobileNumber.text = profileData.phoneNumber
            etName.setText(profileData.name)
            ltAadharNumber.visibility=View.GONE
//            etAadharNumber.setText(profileData.aadharNumber)
            ltDOB.visibility=View.GONE
//            etDOB.setText(profileData.age)
            etAddress.setText(profileData.address)
            etCity.setText(profileData.city)
            etState.setText(profileData.state)
            etCountry.setText(profileData.country)
            etPincode.setText(profileData.pincode)
            etCompanyName.setText(profileData.companyName)
            etCompanyDescription.setText(profileData.companyDescription)
            etCompanyWebsite.setText(profileData.companyWebsiteLink)
            etCompanyContact.setText(profileData.companyContactNumber)
            etCompanyMail.setText(profileData.companyMail)
            etCompanyGSTNO.setText(profileData.companyGSTNo)

            tvGender.visibility=View.GONE
            rgGender.visibility=View.GONE
//            val genderValue = SSProfileData.mLoginData.gender?.toInt()
//
//            when (genderValue) {
//                1 -> rbFemale.isChecked = true
//                2 -> rbMale.isChecked = true
//                0 -> rbOther.isChecked = true
//            }
        }
    }

    private fun getValues() {
        binding.ltJobProvider.apply {
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

            val nAddress = etAddress.text.toString()
            if (isEditTextEmpty(etAddress, context, "Enter Address")) return
            ssRegistrationDetailsData.address = nAddress

            val nCity = etCity.text.toString()
            if (isEditTextEmpty(etCity, context, "Enter City")) return
            ssRegistrationDetailsData.city = nCity

            val nState = etState.text.toString()
            if (isEditTextEmpty(etState, context, "Enter State")) return
            ssRegistrationDetailsData.state = nState

            val nCountry = etCountry.text.toString()
            if (isEditTextEmpty(etCountry, context, "Enter Country")) return
            ssRegistrationDetailsData.country = nCountry

            val nPincode = etPincode.text.toString()
            if (isEditTextEmpty(etPincode, context, "Enter Pincode")) return
            ssRegistrationDetailsData.pinCode = nPincode

            val nCompanyName = etCompanyName.text.toString()
            if (isEditTextEmpty(etCompanyName, context, "Enter Company Name")) return
            ssRegistrationDetailsData.companyName = nCompanyName

            val nCompanyDescription = etCompanyDescription.text.toString()
            if (isEditTextEmpty(etCompanyDescription, context, "Enter Company Description")) return
            ssRegistrationDetailsData.description = nCompanyDescription

            val nCompanyWebsite = etCompanyWebsite.text.toString()
            if (isEditTextEmpty(etCompanyWebsite, context, "Enter Company Website")) return
            ssRegistrationDetailsData.portfolioLink = nCompanyWebsite

            onSubmitClicked.invoke(ssRegistrationDetailsData)
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
        binding.ltJobProvider.apply {
            disableET(etName)
//            disableET(etAadharNumber)
//            disableET(etDOB)
            disableET(etAddress)
            disableET(etCity)
            disableET(etState)
            disableET(etCountry)
            disableET(etPincode)
            disableET(etCompanyName)
            disableET(etCompanyDescription)
            disableET(etCompanyWebsite)
            disableET(etCompanyContact)
            disableET(etCompanyMail)
            disableET(etCompanyGSTNO)

//            rgGender.isEnabled = false
//            rbMale.isEnabled = false
//            rbFemale.isEnabled = false
//            rbOther.isEnabled = false
            btSubmit.visibility = View.GONE
        }
    }

    private fun enableAllEditTexts() {
        binding.ltJobProvider.apply {
            enableET(etName)
//            enableET(etAadharNumber)
//            enableET(etDOB)
            enableET(etAddress)
            enableET(etCity)
            enableET(etState)
            enableET(etCountry)
            enableET(etPincode)
            enableET(etCompanyName)
            enableET(etCompanyDescription)
            enableET(etCompanyWebsite)
            enableET(etCompanyContact)
            enableET(etCompanyMail)
            enableET(etCompanyGSTNO)
//            rgGender.isEnabled = true
//            rbMale.isEnabled = true
//            rbFemale.isEnabled = true
//            rbOther.isEnabled = true
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
        binding.ltJobProvider.rgGender.setOnCheckedChangeListener { group, checkedId ->
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

