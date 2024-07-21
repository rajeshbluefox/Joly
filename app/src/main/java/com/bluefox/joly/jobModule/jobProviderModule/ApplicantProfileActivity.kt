package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityApplicantProfileBinding
import com.bluefox.joly.databinding.ActivityViewApplicationsBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplicantProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicantProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetails()
    }

    private fun setDetails()
    {
        binding.tvNameValue.text=SelJobDetails.applicantProfileData.name
        binding.tvPhoneNumberValue.text=SelJobDetails.applicantProfileData.phoneNumber
        binding.tvDOBValue.text=SelJobDetails.applicantProfileData.age
        binding.tvGenderValue.text=SelJobDetails.applicantProfileData.gender
        binding.tvCityValue.text=SelJobDetails.applicantProfileData.city
        binding.tvStateValue.text=SelJobDetails.applicantProfileData.state
        binding.tvCountryValue.text=SelJobDetails.applicantProfileData.country
        binding.tvQualificationValue.text=SelJobDetails.applicantProfileData.qualification

        if(SelJobDetails.applicantProfileData.gender!!.toInt()==1)
        {
            binding.tvGenderValue.text="Female"
        }else{
            binding.tvGenderValue.text="Male"
        }
    }

    fun onClickListeners()
    {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}