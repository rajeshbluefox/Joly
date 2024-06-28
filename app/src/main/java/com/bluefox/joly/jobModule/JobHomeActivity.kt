package com.bluefox.joly.jobModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.viewJob.ViewWorksFragment
import com.bluefox.joly.databinding.ActivityHomeBinding
import com.bluefox.joly.databinding.ActivityJobHomeBinding
import com.bluefox.joly.jobModule.jobProviderModule.PostJobFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobHomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityJobHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillPostJob()
        onClickListeners()

    }

    private fun onClickListeners() {

        binding.myJobBT.setOnClickListener {

        }

        binding.postJobBT.setOnClickListener {

        }

        binding.profileBT.setOnClickListener {

        }
    }

    private fun fillPostJob() {

        binding.tvAppBarTitle.text="Post Job"

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, PostJobFragment())
            .commit()
    }
}