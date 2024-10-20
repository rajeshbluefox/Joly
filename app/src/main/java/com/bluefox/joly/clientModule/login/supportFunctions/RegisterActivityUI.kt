package com.bluefox.joly.clientModule.login.supportFunctions

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.databinding.ActivityRegisterBinding
import com.bluefox.joly.zSharedPreference.UserDetails
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class RegisterActivityUI(
    val context: Context,
    private val binding: ActivityRegisterBinding,
    private val onSubmitClicked: (sSRegistrationDetailsData: SSRegistrationDetailsData) -> Unit,

    ) {

    private var isTCAccepted = false

    init {

        setFields()
        onClickListeners()
        genderOnClickListener()

    }

    private fun onClickListeners() {

        binding.btSubmit.setOnClickListener {
            getValues()
        }

        binding.cbAgree.setOnCheckedChangeListener { _, b ->
            isTCAccepted = b
        }


    }

    private fun setFields() {
        val userRole = UserDetails.getUserRoleStatus(context)

        when (userRole) {
            4 -> {
                binding.ltJobProvider.visibility = View.GONE
                binding.ltServiceProvider.visibility = View.GONE
                binding.ltJobSeeker.visibility = View.VISIBLE

                binding.tvPinCode.visibility = View.GONE
                binding.etPinCode.visibility = View.GONE

                binding.tvAddress.visibility = View.GONE
                binding.etAddress.visibility = View.GONE

                binding.tvAlternativeNum.visibility = View.GONE
                binding.etAlternativeNumber.visibility = View.GONE
            }

            3 -> {
                binding.ltJobProvider.visibility = View.VISIBLE
                binding.ltServiceProvider.visibility = View.GONE
                binding.ltJobSeeker.visibility = View.GONE

                //Hide AlternativeNo,Aadhaar , DOB, Gender

                binding.tvAlternativeNum.visibility = View.GONE
                binding.etAlternativeNumber.visibility = View.GONE

                binding.tvAadharNum.visibility = View.GONE
                binding.etAadharNum.visibility = View.GONE

                binding.tvDOB.visibility = View.GONE
                binding.etSelectDate.visibility = View.GONE

                binding.tvGender.visibility = View.GONE
                binding.rgGender.visibility = View.GONE
            }
            2 ->{
                binding.ltJobProvider.visibility = View.GONE
                binding.ltServiceProvider.visibility = View.VISIBLE
                binding.ltJobSeeker.visibility = View.GONE

                binding.tvUserName.setText("Business Name")
                binding.etUserName.hint = "Business Name"
            }
            1 -> {
                binding.ltJobProvider.visibility = View.GONE
                binding.ltServiceProvider.visibility = View.VISIBLE
                binding.ltJobSeeker.visibility = View.GONE
                descriptionTextListeners()
            }
        }

//        if (userRole == 3) {
//            binding.ltJobProvider.visibility = View.VISIBLE
//            binding.ltServiceProvider.visibility = View.GONE
//        } else {
//            binding.ltJobProvider.visibility = View.GONE
//            binding.ltServiceProvider.visibility = View.VISIBLE
//        }
    }

    private fun getValues() {

        val nName = binding.etUserName.text.toString()
        val nPhoneNumber = binding.etPhoneNumber.text.toString()
        val nAlternativeNumber = binding.etAlternativeNumber.text.toString()
        val nAadharNum = binding.etAadharNum.text.toString()
        val nDOB = binding.etDOB.text.toString()
        val nSelDOB = binding.etSelectDate.text.toString()
        val nPinCode = binding.etPinCode.text.toString()
        val nAddress = binding.etAddress.text.toString()
        val nPassword = binding.etPassword.text.toString()
        val nConfirmPassword = binding.etConfirmPassword.text.toString()

        val qualification = binding.etQualification.text.toString()
        val experience = binding.etExperience.text.toString()
        val description = binding.etDescription.text.toString()
        val websiteLink = binding.etWebsiteLink.text.toString()
        val workingHours = binding.etWorkingHours.text.toString()

        val companyName = binding.etCompanyName.text.toString()
        val companyContact = binding.etCompanyContact.text.toString()
        val companyEmail = binding.etCompanyMail.text.toString()
        val companyGSTNo = binding.etCompanyGSTNO.text.toString()
        val companyLocation = binding.etCompanyLocation.text.toString()
        val companyDescription = binding.etCompanyDescription.text.toString()
        val companyLink = binding.etCompanyWebLink.text.toString()

        val city = binding.etCity.text.toString()
        val state = binding.etState.text.toString()
        val country = binding.etCountry.text.toString()
        val js_qualification = binding.etQualificationJS.text.toString()
        val skills = binding.etSkills.text.toString()
        val pastExperience = binding.etPastExperienceJS.text.toString()

        if (nName.isEmpty()) {
            UtilFunctions.showToast(context, "Enter Name")
            return
        }
        if (nPhoneNumber.isEmpty()) {
            UtilFunctions.showToast(context, "Enter Phone Number")
            return
        }

        if (SSProfileData.UserRole != 3)
            if (nAadharNum.isEmpty()) {
                UtilFunctions.showToast(context, "Enter AadharNum")
                return
            }

        if (SSProfileData.UserRole != 3)
            if (nDOB.isEmpty()) {
                UtilFunctions.showToast(context, "Enter DOB")
                return

            }
        if (SSProfileData.UserRole != 4) {
            if (nPinCode.isEmpty()) {
                UtilFunctions.showToast(context, "Enter PinCode")
                return

            }
            if (nAddress.isEmpty()) {
                UtilFunctions.showToast(context, "Enter Address")
                return
            }
        }

        if (nPassword.isEmpty()) {
            UtilFunctions.showToast(context, "Enter Password")
            return
        }
        if (nConfirmPassword != nPassword) {
            UtilFunctions.showToast(context, "Passwords doesn't match")
            return
        }

        if (SSProfileData.UserRole != 3)
            if (nGenderSelected == -1) {
                UtilFunctions.showToast(context, "Select Gender")
                return
            }

        val sSRegistrationDetailsData = SSRegistrationDetailsData()

        if (SSProfileData.UserRole == 4) {
            if (isEditTextEmpty(binding.etCity, context, "Enter City")) return
            if (isEditTextEmpty(binding.etState, context, "Enter State")) return
//            if (isEditTextEmpty(binding.etCountry, context, "Enter Country")) return
            if (isEditTextEmpty(binding.etQualificationJS, context, "Enter Qualification")) return
            if (isEditTextEmpty(binding.etSkills, context, "Enter Skills")) return
            if (isEditTextEmpty(
                    binding.etPastExperienceJS,
                    context,
                    "Enter Past Experience"
                )
            ) return

            sSRegistrationDetailsData.city = city
            sSRegistrationDetailsData.state = state
            sSRegistrationDetailsData.country = "India"
            sSRegistrationDetailsData.qualification = js_qualification
            sSRegistrationDetailsData.skills = skills
            sSRegistrationDetailsData.previousExperience = pastExperience
        }


        if (SSProfileData.UserRole == 2) {
            if (isEditTextEmpty(binding.etQualification, context, "Enter Qualification")) return
            if (isEditTextEmpty(binding.etExperience, context, "Enter Experience")) return
            if (isEditTextEmpty(binding.etDescription, context, "Enter Description")) return
            if (isEditTextEmpty(binding.etWorkingHours, context, "Enter Working Hours")) return
            if (isEditTextEmpty(binding.etWebsiteLink, context, "Enter Website Link")) return

            sSRegistrationDetailsData.qualification = qualification
            sSRegistrationDetailsData.previousExperience = experience
            sSRegistrationDetailsData.description = description
            sSRegistrationDetailsData.portfolioLink = websiteLink
            sSRegistrationDetailsData.workingHours = workingHours

            sSRegistrationDetailsData.city = "cty"
            sSRegistrationDetailsData.state = "state"
            sSRegistrationDetailsData.location = "location"
        }

        if (SSProfileData.UserRole == 3) {
            if (isEditTextEmpty(binding.etCompanyName, context, "Enter CompanyName")) return
            if (isEditTextEmpty(binding.etCompanyLocation, context, "Enter CompanyLocation")) return
            if (isEditTextEmpty(
                    binding.etCompanyDescription,
                    context,
                    "Enter CompanyDescription"
                )
            ) return
            if (isEditTextEmpty(binding.etCompanyWebLink, context, "Enter CompanyWebsite")) return
            if (isEditTextEmpty(binding.etCompanyContact, context, "Enter Contact Number")) return
            if (isEditTextEmpty(binding.etCompanyMail, context, "Enter Mail")) return
            if (isEditTextEmpty(binding.etCompanyGSTNO, context, "Enter GSTNo")) return

            sSRegistrationDetailsData.companyName = companyName
            sSRegistrationDetailsData.companyLocation = companyLocation
            sSRegistrationDetailsData.description = companyDescription
            sSRegistrationDetailsData.portfolioLink = companyLink
            sSRegistrationDetailsData.companyContact = companyContact
            sSRegistrationDetailsData.companyMail = companyEmail
            sSRegistrationDetailsData.companyGSTNO = companyGSTNo

            sSRegistrationDetailsData.city = "cty"
            sSRegistrationDetailsData.state = "state"
            sSRegistrationDetailsData.location = "location"
        }

        if (nAlternativeNumber.isEmpty())
            sSRegistrationDetailsData.alternativeNumber = "E"
        else
            sSRegistrationDetailsData.alternativeNumber = nAlternativeNumber

        sSRegistrationDetailsData.name = nName
        sSRegistrationDetailsData.phoneNumber = nPhoneNumber
        sSRegistrationDetailsData.aadharNumber = nAadharNum
        sSRegistrationDetailsData.dateOfBirth = nSelDOB
        sSRegistrationDetailsData.gender = nGenderSelected
        sSRegistrationDetailsData.pinCode = nPinCode
        sSRegistrationDetailsData.address = nAddress
        sSRegistrationDetailsData.password = nPassword
        sSRegistrationDetailsData.confirmPassword = nConfirmPassword

        if (!isTCAccepted) {
            UtilFunctions.showToast(context, "Please Accept Terms and Conditions")
            return
        }

        onSubmitClicked.invoke(sSRegistrationDetailsData)
    }

    fun isEditTextEmpty(editText: EditText, context: Context, message: String): Boolean {
        return if (editText.text.toString().trim().isEmpty()) {
            UtilFunctions.showToast(context, message)
            true
        } else {
            false
        }
    }

    private var nGenderSelected = -1
    private fun genderOnClickListener() {
        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
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

    fun descriptionTextListeners() {
        binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val charCount = s?.length ?: 0
                binding.tvDescription.text = "Description ($charCount/300)"

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


}