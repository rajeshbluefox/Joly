package com.bluefox.joly.clientModule.viewJob

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
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.clientModule.viewJob.modalClass.JobsData
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewJob.supportFunctions.DialogFilter
import com.bluefox.joly.clientModule.viewJob.supportFunctions.JobsAdapter
import com.bluefox.joly.clientModule.viewJob.supportFunctions.ViewJobsUI
import com.bluefox.joly.databinding.FragmentViewJobsBinding
import com.bluefox.joly.zCommonFunctions.CallIntent
import com.bluefox.joly.zSharedPreference.UserDetails
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewWorksFragment : Fragment() {

    private lateinit var binding: FragmentViewJobsBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel

    private lateinit var viewJobsUI: ViewJobsUI

    private lateinit var dialogFilter: DialogFilter

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

    override fun onResume() {
        super.onResume()

        callApis()
        onClickListeners()
        loadSLideShowImages()
    }

    private fun initViews() {
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]
        viewJobsUI = ViewJobsUI(requireContext(), binding, ::getWorkByCategory)

        dialogFilter = DialogFilter(layoutInflater, requireContext(), ::onFilterSelected)

        sSapiFunctions = SSapiFunctions(
            requireContext(),
            viewLifecycleOwner,
            ssViewModel,
            ::categoriesListResponse,
            ::jobsListResponse,
            onWorkSubmitted = {},
            ::onGetWorksResponse,
            onServiceClosed = {}
        )

//        callApis()
    }

    private fun callApis() {

        viewJobsUI.showPB()

        if (ServicesCatJob.isDataFetched) {
            viewJobsUI.initCategoriesSpinner(ServicesCatJob.categoriesList)
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
        else {
            getWorkByCategory(ServicesCatJob.categoriesList[0])
        }

    }

    private fun getWorkByCategory(categoryItem: CategoryItem) {
        sSapiFunctions.getSSWorks(categoryItem.categoryID.toString())
    }

    private var allWorkList = ArrayList<PostWorkData>()

    private fun onGetWorksResponse(worksList: List<PostWorkData>) {
        viewJobsUI.hidePB()
        allWorkList = worksList as ArrayList<PostWorkData>

        if (districtSelected.isNotEmpty()) {
            onFilterSelected(citySelected, districtSelected)
        } else {
            initJobsRv(worksList)
        }

//        if (worksList.isEmpty()) {
//            binding.emptyContent.visibility=View.VISIBLE
//            binding.rvJobs.visibility = View.GONE
//        } else {
//            binding.emptyContent.visibility=View.GONE
//            binding.rvJobs.visibility = View.VISIBLE
//            allWorkList = worksList as ArrayList<PostWorkData>
//            initJobsRv(worksList)
//        }
    }

    private fun loadSLideShowImages() {
        val imageListAdapter = ArrayList<SlideModel>() // Create image list

//        val imagesList = SSSelected.workData.data!!
//
//        for (image in imagesList) {
//            imageListAdapter.add(SlideModel(image!!.url))
//        }

        imageListAdapter.add(SlideModel("https://img.freepik.com/free-vector/abstract-sale-banner-offer-discount-business-background-free-vector_1340-22443.jpg"))
        imageListAdapter.add(SlideModel("https://img.freepik.com/premium-vector/big-sale-banner-template_255749-91.jpg"))
        imageListAdapter.add(SlideModel("https://img.freepik.com/premium-vector/limited-time-offer-sale-banner-sale-banner-promotion-template-design_535749-358.jpg"))


        binding.imageSlider.setImageList(imageListAdapter)
        binding.imageSlider.setImageList(imageListAdapter, ScaleTypes.FIT) // for all images

    }

    private var jobsList = ArrayList<JobsData>()

    private fun initJobsRv(worksList: List<PostWorkData>) {

        if (worksList.isEmpty()) {
            binding.emptyContent.visibility = View.VISIBLE
            binding.rvJobs.visibility = View.GONE
        } else {
            binding.emptyContent.visibility = View.GONE
            binding.rvJobs.visibility = View.VISIBLE
        }

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

    fun onClickListeners() {
        binding.btFilter.setOnClickListener {
            dialogFilter.showFilterDialog()
        }
    }

    private var citySelected = ""
    private var districtSelected = ""

    private fun onFilterSelected(city: String, district: String) {
        Log.e("Test", "SelectedDistrict : $district , SelectedCity : $city")

        citySelected = city
        districtSelected = district

        if (district == "All Districts") {
            binding.btFilter.setImageResource(R.drawable.filter)
            initJobsRv(allWorkList)
        } else {
            binding.btFilter.setImageResource(R.drawable.filter_applied)
            filterList(city, district)
        }
    }

    private var filteredWorkList = ArrayList<PostWorkData>()

    private fun filterList(city: String, district: String) {
        filteredWorkList.clear()

        if (city == "All Cities") {
            for (workItem in allWorkList) {
                if (workItem.district == district) {
                    filteredWorkList.add(workItem)
                }
            }
        } else {
            for (work in allWorkList) {
                if (work.city == city && work.district == district) {
                    filteredWorkList.add(work)
                }
            }
        }

        initJobsRv(filteredWorkList)
    }

}