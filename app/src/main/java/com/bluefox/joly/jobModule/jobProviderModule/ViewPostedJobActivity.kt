package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityJobHomeBinding
import com.bluefox.joly.databinding.ActivityViewPostedJobBinding
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPostedJobActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPostedJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPostedJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setonClickListener()
    }

    private fun setonClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    fun setDetails()
    {
        var postJobData = PostJobData()

        binding.etJobName.setText(postJobData.jobName)
        binding.etJobDetails.setText(postJobData.jobDetails)
        binding.etJobDescription.setText(postJobData.jobDescription)
        binding.etEligibility.setText(postJobData.eligibility)
        binding.etSkillsRequired.setText(postJobData.skills)
        binding.tvDeadLineToApplyValue.setText(postJobData.deadLineToApply)
    }
}