package com.bluefox.joly.clientModule.viewJob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluefox.joly.R
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding

class ViewJobsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewJobDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetails()
    }

    private fun setDetails() {

    }
}