package com.bluefox.joly.clientModule.viewJob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.viewJob.modalClass.JobsData
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewJob.supportFunctions.JobsAdapter
import com.bluefox.joly.databinding.FragmentViewJobsBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewJobsFragment : Fragment() {

    private lateinit var binding: FragmentViewJobsBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_jobs, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]
        sSapiFunctions = SSapiFunctions(
            requireContext(),
            viewLifecycleOwner,
            ssViewModel,
            onCategoriesResponse = {},
            onJobsResponse = {},
            onWorkSubmitted = {},
            ::onGetWorksResponse
        )

        getSSWorks()
    }

    fun getSSWorks() {
        sSapiFunctions.getSSWorks(UserDetails.getUserMobileNo(requireContext()))
    }

    fun onGetWorksResponse(worksList: List<PostWorkData>) {
        initJobsRv(worksList)
    }

    private fun fillDummyList() {
        val jobsData1 = JobsData()

        jobsData1.id = "1"
        jobsData1.jobName = "Software Developer"

        jobsList.add(jobsData1)

        val jobsData2 = JobsData()

        jobsData2.id = "2"
        jobsData2.jobName = "Test Developer"

        jobsList.add(jobsData2)

        val jobsData3 = JobsData()

        jobsData3.id = "3"
        jobsData3.jobName = "Web Developer"

        val jobsData4 = JobsData()

        jobsData4.id = "3"
        jobsData4.jobName = "Cloud Developer"

        val jobsData5 = JobsData()

        jobsData5.id = "3"
        jobsData5.jobName = "Mobile Developer"

        val jobsData6 = JobsData()

        jobsData6.id = "3"
        jobsData6.jobName = "Agile Developer"

        jobsList.add(jobsData3)
        jobsList.add(jobsData4)
        jobsList.add(jobsData5)
        jobsList.add(jobsData6)
    }

    private var jobsList = ArrayList<JobsData>()

    private fun initJobsRv(worksList: List<PostWorkData>) {
//        fillDummyList()

        val jobsAdapter = JobsAdapter(requireContext(), worksList, ::onJobClicked)
        binding.rvJobs.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = jobsAdapter
        }

    }

    private fun onJobClicked(jobItem: PostWorkData) {
        SSSelected.workData=jobItem
        CallIntent.gotoViewJobDetails(requireContext(), false, requireActivity())
    }


}