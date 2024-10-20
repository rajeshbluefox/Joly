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


class JSUI(
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

        binding.ltJobSeeker.ivEdit.setOnClickListener {
            enableAllEditTexts()
        }

        binding.ltJobSeeker.ivLogout.setOnClickListener {
            logoutClicked.invoke()
        }

        binding.ltJobSeeker.btSubmit.setOnClickListener {
            getValues()
        }

        binding.ltJobSeeker.profilePic.setOnClickListener {
            CallIntent.gotoProfileImageActivity(context, false, activity)
        }

    }

    private fun setDetails() {
        val profileData = SSProfileData.mLoginData

        binding.ltJobSeeker.apply {
            tvMobileNumber.text = profileData.phoneNumber
            etName.setText(profileData.name)
            etAadharNumber.setText(profileData.aadharNumber)
            etDOB.setText(profileData.age)
            etCity.setText(profileData.city)
            etState.setText(profileData.state)
            etCountry.setText(profileData.country)
            etPastExperience.setText(profileData.pastExperience)
            etQualification.setText(profileData.qualification)
            etSkills.setText(profileData.skills)

            val genderValue = SSProfileData.mLoginData.gender?.toInt()

            when (genderValue) {
                1 -> rbFemale.isChecked = true
                2 -> rbMale.isChecked = true
                0 -> rbOther.isChecked = true
            }
        }
    }

    private fun getValues() {
        binding.ltJobSeeker.apply {
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

            val nCity = etCity.text.toString()
            if (isEditTextEmpty(etCity, context, "Enter City")) return
            ssRegistrationDetailsData.location = nCity

            val nState = etState.text.toString()
            if (isEditTextEmpty(etState, context, "Enter State")) return
            ssRegistrationDetailsData.state = nState

            val nCountry = etCountry.text.toString()
            if (isEditTextEmpty(etCountry, context, "Enter Country")) return
            ssRegistrationDetailsData.country = nCountry

            val nQualification = etQualification.text.toString()
            if (isEditTextEmpty(etQualification, context, "Enter Qualification")) return
            ssRegistrationDetailsData.qualification = nQualification

            val nSkills = etSkills.text.toString()
            if (isEditTextEmpty(etSkills, context, "Enter Skills")) return
            ssRegistrationDetailsData.skills = nSkills

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
        binding.ltJobSeeker.apply {
            disableET(etName)
            disableET(etAadharNumber)
            disableET(etDOB)
            disableET(etCity)
            disableET(etState)
            disableET(etCountry)
            disableET(etQualification)
            disableET(etSkills)
            disableET(etPastExperience)
            rgGender.isEnabled = false
            rbMale.isEnabled = false
            rbFemale.isEnabled = false
            rbOther.isEnabled = false
            btSubmit.visibility = View.GONE
        }
    }

    private fun enableAllEditTexts() {
        binding.ltJobSeeker.apply {
            enableET(etName)
            enableET(etAadharNumber)
            enableET(etDOB)
            enableET(etCity)
            enableET(etState)
            enableET(etCountry)
            enableET(etQualification)
            enableET(etSkills)
            enableET(etPastExperience)
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
