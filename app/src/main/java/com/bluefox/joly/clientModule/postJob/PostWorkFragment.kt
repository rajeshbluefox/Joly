package com.bluefox.joly.clientModule.postJob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.postJob.adapters.CategorySpinnerAdapter
import com.bluefox.joly.clientModule.postJob.adapters.JobsSpinnerAdapter
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.viewJob.supportFunctions.PostWorkUI
import com.bluefox.joly.databinding.FragmentPostWorkBinding
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostWorkFragment : Fragment() {

    private lateinit var binding: FragmentPostWorkBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel

    private lateinit var postWorkUI: PostWorkUI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_work, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]
        postWorkUI = PostWorkUI(requireContext(), binding, ::postWorkDetails)
        sSapiFunctions = SSapiFunctions(
            requireContext(),
            viewLifecycleOwner,
            ssViewModel,
            ::categoriesListResponse,
            ::jobsListResponse,
            ::onWorkSubmitted
        )

        callApis()
    }

    private fun callApis() {
        postWorkUI.showPB()
        sSapiFunctions.getCategories()
    }

    private fun jobsListResponse(jobsList: List<JobItem>) {
        postWorkUI.initJobsSpinner(jobsList)
        postWorkUI.hidePB()
    }

    private fun categoriesListResponse(categoriesList: List<CategoryItem>) {
        postWorkUI.initCategoriesSpinner(categoriesList)

        //Calling Jobs API
        sSapiFunctions.getJobs()
    }


    private fun postWorkDetails(postWorkData: PostWorkData) {
        sSapiFunctions.postWork(postWorkData)
    }

    private fun onWorkSubmitted()
    {
        UtilFunctions.showToast(requireContext(),"Work Submitted Successfully")
    }

}