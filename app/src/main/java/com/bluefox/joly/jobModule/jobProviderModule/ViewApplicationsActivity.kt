package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.databinding.ActivityViewApplicationsBinding
import com.bluefox.joly.databinding.ActivityViewPostedJobBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.JpJobsAdapter
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.ViewApplicationAdapter
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.ViewPostedJobUI
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

        initViews()
    }

    fun initViews() {

        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]

        jPapiFunctions = JPapiFunctions(
            this,
            lifecycleOwner = this,
            jpViewModel,
            onJobPostedResponse ={},
            onGetPostedJobsResponse = {}
        )
    }

    private fun initViewApplicationsRV(applicationsList: List<LoginData>) {

        Log.e("Test","3")

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

    }
}