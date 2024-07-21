package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.apiFunctions.LoginViewModel
import com.bluefox.joly.databinding.FragmentPostJobBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.PostJobUI
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostJobFragment : Fragment() {

    private lateinit var binding: FragmentPostJobBinding

    private lateinit var postJobUI: PostJobUI
    private lateinit var jPapiFunctions: JPapiFunctions
    private lateinit var jpViewModel: JPViewModel

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_job, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
    }

    fun initViews() {
        postJobUI = PostJobUI(requireContext(), binding, ::onPostJobClicked)
        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        jPapiFunctions = JPapiFunctions(
            requireContext(),
            lifecycleOwner = this,
            jpViewModel,
            ::onJobPostedResponse,
            onGetPostedJobsResponse = {},
            onViewApplicationResponse = {},
            onGetAllJobs = {}
        )
    }


    private fun onPostJobClicked(postJobData: PostJobData) {
        jPapiFunctions.postJob(postJobData)
    }

    private fun onJobPostedResponse() {
        //Code after Job Posted response is received
        loginViewModel.setCurrentFragment(1)
    }

}