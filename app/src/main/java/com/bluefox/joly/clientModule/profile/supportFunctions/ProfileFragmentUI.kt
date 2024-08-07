//package com.bluefox.joly.clientModule.profile.supportFunctions
//
//import android.app.Activity
//import android.content.Context
//import android.view.View
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.bluefox.joly.R
//import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
//import com.bluefox.joly.clientModule.profile.modalClass.SSProfileDetailsData
//import com.bluefox.joly.databinding.FragmentProfileBinding
//import com.bluefox.joly.zCommonFunctions.CallIntent
//import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
//
//
//class ProfileFragmentUI(
//    val context: Context,
//    val activity: Activity,
//    private val binding: FragmentProfileBinding,
//    private val onSubmitClicked: (sSProfileDetailsData :SSProfileDetailsData) -> Unit,
//    private val logoutClicked: () -> Unit
//
//    ) {
//
//    init {
//
//        setData()
//        stopEditing()
//        onClickListeners()
//        genderOnClickListener()
//    }
//
//    private fun setData()
//    {
//
//        when(SSProfileData.UserRole)
//        {
//            1 ->{
//                binding.etName.setText(SSProfileData.mLoginData.name)
//
//                binding.ltServiceProvider.visibility=View.GONE
//                binding.ltJobProvider.visibility=View.GONE
//                binding.ltJobSeeker.visibility=View.GONE
//            }
//            2 ->{
//                binding.etName.setText(SSProfileData.mLoginData.companyName)
//
//                binding.ltServiceProvider.visibility=View.VISIBLE
//                binding.ltJobProvider.visibility=View.GONE
//                binding.ltJobSeeker.visibility=View.GONE
//
//                binding.etQualification.setText(SSProfileData.mLoginData.qualification)
//                binding.etExpeienece.setText(SSProfileData.mLoginData.previousExperience)
//                binding.etDescription.setText(SSProfileData.mLoginData.description)
//                binding.etWebsiteLink.setText(SSProfileData.mLoginData.portfolioLink)
//            }
//            3 ->{
//                binding.etName.setText(SSProfileData.mLoginData.name)
//
//                binding.ltServiceProvider.visibility=View.GONE
//                binding.ltJobProvider.visibility=View.VISIBLE
//                binding.ltJobSeeker.visibility=View.GONE
//
//                binding.etCompanyName.setText(SSProfileData.mLoginData.companyName)
//                binding.etCompanyLocation.setText("${SSProfileData.mLoginData.city},${SSProfileData.mLoginData.state},${SSProfileData.mLoginData.country}")
//                binding.etCompanyDescription.setText(SSProfileData.mLoginData.companyDescription)
//                binding.etCompanyWebsiteLink.setText(SSProfileData.mLoginData.companyWebsiteLink)
//            }
//            4 ->{
//                binding.ltServiceProvider.visibility=View.GONE
//                binding.ltJobProvider.visibility=View.GONE
//                binding.ltJobSeeker.visibility=View.VISIBLE
//
//                binding.ltPinCode.visibility=View.GONE
//                binding.ltAddress.visibility=View.GONE
//
//                binding.etCity.setText(SSProfileData.mLoginData.city)
//                binding.etState.setText(SSProfileData.mLoginData.state)
//                binding.etCountry.setText(SSProfileData.mLoginData.country)
//                binding.etQualificationJS.setText(SSProfileData.mLoginData.qualification)
//                binding.etSkills.setText(SSProfileData.mLoginData.skills)
//            }
//        }
//
//
//        binding.tvMobileNumber.text = SSProfileData.mLoginData.phoneNumber
//        binding.etAadharNum.setText(SSProfileData.mLoginData.aadharNumber)
//        binding.etDOB.setText(SSProfileData.mLoginData.age)
//        binding.etPinCode.setText(SSProfileData.mLoginData.pincode)
//        binding.etAddress.setText(SSProfileData.mLoginData.address)
//
//        val genderValue = SSProfileData.mLoginData.gender?.toInt()
//
//        when(genderValue)
//        {
//            1 -> binding.rbFemale.isChecked = true
//            2 -> binding.rbMale.isChecked = true
//            0 -> binding.rbOther.isChecked = true
//        }
//
//    }
//
//    private fun onClickListeners() {
//        binding.ivEdit.setOnClickListener {
//            startEditing()
//        }
//
//        binding.btSubmit.setOnClickListener {
//
//            stopEditing()
//            getValues()
//        }
//
//        binding.ivLogout.setOnClickListener{
//            logoutClicked.invoke()
//        }
//
//        binding.profilePic.setOnClickListener {
//            CallIntent.gotoProfileImageActivity(context,false,activity)
//        }
//    }
//
//    private fun getValues()
//    {
//
//        val nName = binding.etName.text.toString()
//        val nAadharNum = binding.etAadharNum.text.toString()
//        val nDOB = binding.etDOB.text.toString()
//        val nPinCode = binding.etPinCode.text.toString()
//        val nAddress = binding.etAddress.text.toString()
//
//        if(nName.isEmpty())
//        {
//            UtilFunctions.showToast(context, "Enter Name")
//            return
//        }
//        if (nAadharNum.isEmpty())
//        {
//            UtilFunctions.showToast(context, "Enter AadharNum")
//            return
//        }
//        if (nDOB.isEmpty())
//        {
//            UtilFunctions.showToast(context, "Enter DOB")
//            return
//
//        }
//        if (nPinCode.isEmpty())
//        {
//            UtilFunctions.showToast(context, "Enter PinCode")
//            return
//
//        }
//        if (nAddress.isEmpty())
//        {
//            UtilFunctions.showToast(context, "Enter Address")
//            return
//        }
//        if (nGenderSelected==-1)
//        {
//            UtilFunctions.showToast(context, "Select Gender")
//            return
//        }
//
//        val sSProfileDetailsData = SSProfileDetailsData()
//        sSProfileDetailsData.name= nName
//        sSProfileDetailsData.aadharNumber=nAadharNum
//        sSProfileDetailsData.dateOfBirth=nDOB
//        sSProfileDetailsData.gender=nGenderSelected
//        sSProfileDetailsData.pinCode=nPinCode
//        sSProfileDetailsData.address=nAddress
//
//
//        onSubmitClicked.invoke(sSProfileDetailsData)
//    }
//
//    private fun stopEditing() {
////        binding.ivEdit.visibility = View.VISIBLE
//        binding.btSubmit.visibility = View.GONE
//
//        disableET(binding.ltName)
//        disableET(binding.ltAadharNum)
//        disableET(binding.ltDOB)
//        disableET(binding.rgGender)
//        disableET(binding.ltPinCode)
//        disableET(binding.ltAddress)
//
//        disableET(binding.ltQualification)
//        disableET(binding.ltExperience)
//        disableET(binding.ltDescription)
//        disableET(binding.ltWebsiteLink)
//
//        disableET(binding.ltCompanyName)
//        disableET(binding.ltCompanyLocation)
//        disableET(binding.ltCompanyDescription)
//        disableET(binding.ltCompanyWebsiteLink)
//
//        disableET(binding.ltCity)
//        disableET(binding.ltState)
//        disableET(binding.ltCountry)
//        disableET(binding.ltQualificationJS)
//        disableET(binding.ltSkills)
//
//        binding.rgGender.isEnabled = false
//        binding.rbMale.isEnabled = false
//        binding.rbFemale.isEnabled = false
//        binding.rbOther.isEnabled = false
////        ic_down_arrow
//    }
//
//    private fun startEditing() {
//        binding.ivEdit.visibility = View.GONE
//        binding.btSubmit.visibility = View.VISIBLE
//
//        enableET(binding.ltName)
//        enableET(binding.ltAadharNum)
//        enableET(binding.ltDOB)
//        enableET(binding.rgGender)
//        enableET(binding.ltPinCode)
//        enableET(binding.ltAddress)
//
//        enableET(binding.ltCompanyLocation)
//    }
//
//    private fun disableET(view: View) {
////        view.isFocusable = false;
//        view.isClickable = false;
//        view.isEnabled = false
//    }
//
//    private fun enableET(view: View) {
////        view.isFocusable = true;
//        view.isClickable = true;
//        view.isEnabled = true;
//    }
//
//    fun setBottomMargin(view: View,margin : Int) {
//        // Assuming tabLayout is your TabLayout instance
//        val params = view.layoutParams as ConstraintLayout.LayoutParams
//
//        params.topMargin = margin
//        view.layoutParams = params
//    }
//
//    private var nGenderSelected = -1
//    private fun genderOnClickListener()
//    {
//        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
//            when (checkedId) {
//                R.id.rbFemale -> {
//                    nGenderSelected=1
//
//                }
//                R.id.rbMale -> {
//                    nGenderSelected=2
//                }
//                R.id.rbOther -> {
//                    nGenderSelected=0
//                }
//            }
//        }
//    }
//
//
//
//
//}
