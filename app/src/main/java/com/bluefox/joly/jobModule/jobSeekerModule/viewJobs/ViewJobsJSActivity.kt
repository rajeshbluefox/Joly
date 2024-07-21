package com.bluefox.joly.jobModule.jobSeekerModule.viewJobs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityViewApplicationsBinding
import com.bluefox.joly.databinding.ActivityViewJobsJsactivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewJobsJSActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewJobsJsactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobsJsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}