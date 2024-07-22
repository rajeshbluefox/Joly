package com.bluefox.joly.jobModule.jobSeekerModule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.databinding.FragmentViewAllJobsBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.JpJobsAdapter
import com.bluefox.joly.zCommonFunctions.CallIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewAllJobsFragment : Fragment() {

    private lateinit var binding: FragmentViewAllJobsBinding

    private lateinit var jPapiFunctions: JPapiFunctions
    private lateinit var jpViewModel: JPViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_all_jobs, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onResume() {
        super.onResume()

        jPapiFunctions.getAllJobs(SSProfileData.mLoginData.userId.toString())
    }


    private fun initViews() {
        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]

        jPapiFunctions = JPapiFunctions(
            requireContext(),
            lifecycleOwner = this,
            jpViewModel,
            onJobPostedResponse = {},
            onGetPostedJobsResponse = {},
            onViewApplicationResponse = {},
            ::onJobsFetched,
            onApplyJobs = {}
        )
    }

    private fun onJobsFetched(jobsList: ArrayList<PostJobData>) {
        initPostedJobs(jobsList)
    }


    private fun initPostedJobs(worksList: List<PostJobData>) {

        Log.e("Test", "3")

        val jpJobsAdapter = JpJobsAdapter(requireContext(), worksList, ::onJobClicked)
        binding.rvJobs.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = jpJobsAdapter
        }

    }

    private fun onJobClicked(postJobData: PostJobData) {
        //Call ViewPostedJob
        SelJobDetails.postJobData = postJobData
        //Call intent to ViewJob
        CallIntent.gotoJSViewJobActivity(requireContext(), false, requireActivity())
    }
}