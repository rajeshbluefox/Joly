package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityApplicantProfileBinding
import com.bluefox.joly.databinding.ActivityViewApplicationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplicantProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApplicantProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplicantProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}