package com.bluefox.joly.serviceProviderModule

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
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.databinding.FragmentSPAddServicesBinding
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPAPIFunctions
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPViewModel
import com.bluefox.joly.serviceProviderModule.modelClass.AddServicesSelectedJobs
import com.bluefox.joly.serviceProviderModule.modelClass.ServicesOfferedData
import com.bluefox.joly.serviceProviderModule.supportFunctions.ServicesOfferedAdapter
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SPAddServicesFragment : Fragment() {

    private lateinit var binding: FragmentSPAddServicesBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel
    private lateinit var spViewModel: SPViewModel

    private lateinit var spapiFunctions: SPAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_s_p_add_services, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btAddService.setOnClickListener {
            AddServicesSelectedJobs.selectedPage=1
            CallIntent.gotoAddServiceActivity(requireContext(),false,requireActivity())
        }
        binding.btMyTestimonials.setOnClickListener {
            CallIntent.gotoShowTestimonialsActivity(requireContext(),false,requireActivity())
        }
    }

    fun initViews() {
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]
        spViewModel = ViewModelProvider(this)[SPViewModel::class.java]

        sSapiFunctions = SSapiFunctions(
            requireContext(),
            viewLifecycleOwner,
            ssViewModel,
            ::categoriesListResponse,
            ::jobsListResponse,
            onWorkSubmitted = {},
            onGetWorksResponse = {}
        )

        spapiFunctions = SPAPIFunctions(
            requireContext(),
            viewLifecycleOwner,
            spViewModel,
            ::onGetServices,
            ::onTestimonyPostedResponse,
            onServiceAddedResponse = {},
            onGetTestimonials = {}
        )

        callApis()
    }

    private fun onGetServices(servicesOfferedList: List<ServicesOfferedData>) {

        AddServicesSelectedJobs.providingCategories=servicesOfferedList as ArrayList<ServicesOfferedData>

        val servicesOfferedAdapter = ServicesOfferedAdapter(requireContext(), servicesOfferedList, ::onCategoryClicked)
        binding.rvServicesOffered.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = servicesOfferedAdapter
        }
    }

    private fun onCategoryClicked(servicesOfferedData: ServicesOfferedData)
    {
        AddServicesSelectedJobs.selService=servicesOfferedData
        AddServicesSelectedJobs.selectedPage=2
        CallIntent.gotoAddServiceActivity(requireContext(),false,requireActivity())
    }

    private fun onTestimonyPostedResponse() {

    }

    private fun callApis() {
        if (ServicesCatJob.isDataFetched) {

            val mobileNo = UserDetails.getUserMobileNo(requireContext())
            spapiFunctions.getServicesOffered(mobileNo)
            //
//            postWorkUI.initCategoriesSpinner(ServicesCatJob.categoriesList)
//            postWorkUI.initJobsSpinner(ServicesCatJob.jobList)
        } else {
            showPB()
            sSapiFunctions.getCategories()
        }


    }

    private fun showPB() {
        binding.progressBar.visibility = View.VISIBLE
        binding.mainLt.visibility = View.GONE
    }

    private fun hidePB() {
        binding.progressBar.visibility = View.GONE
        binding.mainLt.visibility = View.VISIBLE
    }

    private fun jobsListResponse(jobsList: List<JobItem>) {
        ServicesCatJob.isDataFetched = true

        ServicesCatJob.jobList = jobsList as ArrayList<JobItem>

        val mobileNo = UserDetails.getUserMobileNo(requireContext())
        spapiFunctions.getServicesOffered(mobileNo)
        hidePB()
    }

    private fun categoriesListResponse(categoriesList: List<CategoryItem>) {

        ServicesCatJob.categoriesList = categoriesList as ArrayList<CategoryItem>
        Log.e("Test","Cat List Frag ${ServicesCatJob.categoriesList}")
        //Calling Jobs API
        sSapiFunctions.getJobs()
    }
}