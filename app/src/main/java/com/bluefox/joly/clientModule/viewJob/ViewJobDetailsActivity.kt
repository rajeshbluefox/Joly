package com.bluefox.joly.clientModule.viewJob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluefox.joly.clientModule.viewJob.modalClass.JobSelected
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding

class ViewJobDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewJobDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetails()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setDetails() {
        binding.tvJobTitle.text=JobSelected.jobsData.jobName
    }
}