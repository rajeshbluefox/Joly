package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityJobHomeBinding
import com.bluefox.joly.databinding.ActivityViewPostedJobBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.PostJobUI
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.ViewPostedJobUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPostedJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPostedJobBinding

    private lateinit var viewPostedJobUI: ViewPostedJobUI

    private lateinit var jPapiFunctions: JPapiFunctions
    private lateinit var jpViewModel: JPViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPostedJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClickListeners()
    }

    fun initViews() {
        viewPostedJobUI = ViewPostedJobUI(this, binding, ::onUpdateJobClicked, ::onUpdateJobStatus)
        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]

        jPapiFunctions = JPapiFunctions(
            this,
            lifecycleOwner = this,
            jpViewModel,
            onJobPostedResponse ={},
            onGetPostedJobsResponse = {}
        )
    }

    fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun onUpdateJobStatus(jobId: String, jobStatus: String) {
        jPapiFunctions.getPostedJobStatus(jobId,jobStatus)

    }

    private fun onUpdateJobClicked(postJobData: PostJobData) {
        jPapiFunctions.updateJob(postJobData)

    }


}