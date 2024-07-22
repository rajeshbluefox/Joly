package com.bluefox.joly.jobModule.jobSeekerModule.viewJobs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.ActivityViewJobsJsactivityBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewJobsJSActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewJobsJsactivityBinding

    private lateinit var jPapiFunctions: JPapiFunctions
    private lateinit var jpViewModel: JPViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobsJsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        initViews()
        searchIfApplied()
        setDetails()
        onClickListeners()
    }

    private fun searchIfApplied()
    {

        if(SelJobDetails.selFragmentJobSeeker==1)
        {
            binding.btApply.text = "Applied"
            binding.btApply.isEnabled = false
        }
        else
        {
            binding.btApply.text = "Apply"
            binding.btApply.isEnabled = true
        }
    }

    private fun setDetails()
    {
        val postJobData = SelJobDetails.postJobData

        binding.tvJobNameValue.text = postJobData.jobName
        binding.tvJobDetailsValue.text = postJobData.jobDetails
        binding.tvJobDescriptionValue.text = postJobData.jobDescription
        binding.tvEligibilityValue.text = postJobData.eligibility
        binding.tvDeadLineToApplyValue.text = postJobData.deadLineToApply
        binding.tvSkillsRequiredValue.text = postJobData.skills

    }

    private fun initViews() {
        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]

        jPapiFunctions = JPapiFunctions(
            this,
            lifecycleOwner = this,
            jpViewModel,
            onJobPostedResponse = {},
            onGetPostedJobsResponse = {},
            onViewApplicationResponse = {},
            onGetAllJobs = {},
            ::onJobAppliedResponse
        )
    }

    private fun onJobAppliedResponse()
    {
        UtilFunctions.showToast(this,"Job Applied Successfully")
    }


    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btApply.setOnClickListener {
            jPapiFunctions.jsApplyJobs(SelJobDetails.postJobData.jobId.toString(), SSProfileData.mLoginData.userId.toString())
        }
    }
}