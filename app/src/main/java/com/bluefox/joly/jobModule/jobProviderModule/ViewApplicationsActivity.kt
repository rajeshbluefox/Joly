package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.databinding.ActivityViewApplicationsBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.ViewApplicationAdapter
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewApplicationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewApplicationsBinding

    private lateinit var jPapiFunctions: JPapiFunctions
    private lateinit var jpViewModel: JPViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewApplicationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBar(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        initViews()
        onClickListeners()
    }

    fun onClickListeners()
    {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    fun initViews() {

        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]

        jPapiFunctions = JPapiFunctions(
            this,
            lifecycleOwner = this,
            jpViewModel,
            onJobPostedResponse ={},
            onGetPostedJobsResponse = {},
            ::initViewApplicationsRV,
            onGetAllJobs = {},
            onApplyJobs = {}
        )

        jPapiFunctions.getJobApplications(SelJobDetails.postJobData.jobId.toString())
    }

    private fun initViewApplicationsRV(applicationsList: List<LoginData>) {

        Log.e("Test","3")

        if (applicationsList.isEmpty()) {
            binding.emptyContent.visibility = View.VISIBLE
            binding.rvViewApplication.visibility = View.GONE
        } else {
            binding.emptyContent.visibility = View.GONE
            binding.rvViewApplication.visibility = View.VISIBLE
        }

        val viewApplicationAdapter = ViewApplicationAdapter(this, applicationsList, ::onApplicationClicked)
        binding.rvViewApplication.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = viewApplicationAdapter
        }

    }

    private fun onApplicationClicked(loginData: LoginData) {
        SelJobDetails.applicantProfileData=loginData
        CallIntent.gotoApplicantProfileActivity(this, false, this)
    }
}