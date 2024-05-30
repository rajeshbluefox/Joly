package com.bluefox.joly.clientModule.login.supportFunctions

import android.content.Context
import android.widget.EditText
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.databinding.ActivityRegisterBinding
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class RegisterActivityUI(
    val context: Context,
    private val binding: ActivityRegisterBinding,
    private val onSubmitClicked: (sSRegistrationDetailsData : SSRegistrationDetailsData) -> Unit,

    ) {

    init {

        onClickListeners()
        genderOnClickListener()

    }

    private fun onClickListeners() {

        binding.btSubmit.setOnClickListener {
            getValues()
        }
    }

    private fun getValues()
    {

        val nName = binding.etUserName.text.toString()
        val nPhoneNumber = binding.etPhoneNumber.text.toString()
        val nAadharNum = binding.etAadharNum.text.toString()
        val nDOB = binding.etDOB.text.toString()
        val nPinCode = binding.etPinCode.text.toString()
        val nAddress = binding.etAddress.text.toString()
        val nPassword = binding.etPassword.text.toString()
        val nConfirmPassword =binding.etConfirmPassword.text.toString()

        val qualification = binding.etQualification.text.toString()
        val experience = binding.etExperience.text.toString()
        val description = binding.etDescription.text.toString()
        val websiteLink = binding.etWebsiteLink.text.toString()

        if(nName.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter Name")
            return
        }
        if (nPhoneNumber.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter Phone Number")
            return
        }
        if (nAadharNum.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter AadharNum")
            return
        }
        if (nDOB.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter DOB")
            return

        }
        if (nPinCode.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter PinCode")
            return

        }
        if (nAddress.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter Address")
            return
        }
        if (nPassword.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter Password")
            return
        }
        if (nConfirmPassword.isEmpty())
        {
            UtilFunctions.showToast(context, "Enter ConfirmPassword")
            return
        }
        if (nGenderSelected==-1)
        {
            UtilFunctions.showToast(context, "Select Gender")
            return
        }

        val sSRegistrationDetailsData = SSRegistrationDetailsData()


        if(SSProfileData.UserRole==2)
        {
            if (isEditTextEmpty(binding.etQualification, context, "Enter Qualification")) return
            if (isEditTextEmpty(binding.etExperience, context, "Enter Experience")) return
            if (isEditTextEmpty(binding.etDescription, context, "Enter Description")) return
            if (isEditTextEmpty(binding.etWebsiteLink, context, "Enter Website Link")) return

            sSRegistrationDetailsData.qualification=qualification
            sSRegistrationDetailsData.previousExperience=experience
            sSRegistrationDetailsData.description=description
            sSRegistrationDetailsData.portfolioLink=websiteLink

            sSRegistrationDetailsData.city="cty"
            sSRegistrationDetailsData.state="state"
            sSRegistrationDetailsData.location="location"
        }

        sSRegistrationDetailsData.name= nName
        sSRegistrationDetailsData.phoneNumber=nPhoneNumber
        sSRegistrationDetailsData.aadharNumber=nAadharNum
        sSRegistrationDetailsData.dateOfBirth=nDOB
        sSRegistrationDetailsData.gender=nGenderSelected
        sSRegistrationDetailsData.pinCode=nPinCode
        sSRegistrationDetailsData.address=nAddress
        sSRegistrationDetailsData.password=nPassword
        sSRegistrationDetailsData.confirmPassword=nConfirmPassword

        onSubmitClicked.invoke(sSRegistrationDetailsData)
    }

    fun isEditTextEmpty(editText: EditText, context: Context, message: String): Boolean {
        return if (editText.text.toString().trim().isEmpty()) {
            UtilFunctions.showToast(context,message)
            true
        } else {
            false
        }
    }

    private var nGenderSelected = -1
    private fun genderOnClickListener()
    {
        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbFemale -> {
                    nGenderSelected=1

                }
                R.id.rbMale -> {
                    nGenderSelected=2
                }
                R.id.rbOther -> {
                    nGenderSelected=0
                }
            }
        }
    }


}