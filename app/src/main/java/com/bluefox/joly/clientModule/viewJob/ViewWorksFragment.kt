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
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.clientModule.viewJob.modalClass.JobsData
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewJob.supportFunctions.JobsAdapter
import com.bluefox.joly.clientModule.viewJob.supportFunctions.ViewJobsUI
import com.bluefox.joly.databinding.FragmentViewJobsBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewWorksFragment : Fragment() {

    private lateinit var binding: FragmentViewJobsBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel

    private lateinit var viewJobsUI: ViewJobsUI


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
        viewJobsUI = ViewJobsUI(requireContext(), binding,::getWorkByCategory)

        sSapiFunctions = SSapiFunctions(
            requireContext(),
            viewLifecycleOwner,
            ssViewModel,
            ::categoriesListResponse,
            ::jobsListResponse,
            onWorkSubmitted = {},
            ::onGetWorksResponse
        )

        callApis()
    }

    private fun callApis() {
        viewJobsUI.showPB()

        if (ServicesCatJob.isDataFetched) {
            getSSWorks()
        } else {
            sSapiFunctions.getCategories()
        }
    }


    private fun jobsListResponse(jobsList: List<JobItem>) {
        ServicesCatJob.jobList = jobsList as ArrayList<JobItem>
        ServicesCatJob.isDataFetched = true

        viewJobsUI.initCategoriesSpinner(ServicesCatJob.categoriesList)

        getSSWorks()
    }

    private fun categoriesListResponse(categoriesList: List<CategoryItem>) {
        //Calling Jobs API
        ServicesCatJob.categoriesList = categoriesList as ArrayList<CategoryItem>
        sSapiFunctions.getJobs()
    }

    private fun getSSWorks() {
        if (SSProfileData.UserRole == 1)
            sSapiFunctions.getSSWorks(UserDetails.getUserMobileNo(requireContext()))

    }

    private fun getWorkByCategory(categoryItem: CategoryItem)
    {
        sSapiFunctions.getSSWorks(categoryItem.categoryID.toString())
    }

    fun onGetWorksResponse(worksList: List<PostWorkData>) {
        viewJobsUI.hidePB()

        if (worksList.isEmpty()) {
            binding.emptyList.visibility = View.VISIBLE
            binding.rvJobs.visibility= View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.rvJobs.visibility= View.VISIBLE
            initJobsRv(worksList)
        }
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
        SSSelected.workData = jobItem
        CallIntent.gotoViewJobDetails(requireContext(), false, requireActivity())
    }


}