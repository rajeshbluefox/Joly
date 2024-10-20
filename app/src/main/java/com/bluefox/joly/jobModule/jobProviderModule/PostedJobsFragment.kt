package com.bluefox.joly.jobModule.jobProviderModule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.R
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.viewJob.supportFunctions.DialogFilter
import com.bluefox.joly.databinding.FragmentPostedJobsBinding
import com.bluefox.joly.jobModule.apiFunctions.JPViewModel
import com.bluefox.joly.jobModule.apiFunctions.JPapiFunctions
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.jobModule.jobProviderModule.supportFunctions.JpJobsAdapter
import com.bluefox.joly.zCommonFunctions.CallIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostedJobsFragment : Fragment() {

    private lateinit var binding: FragmentPostedJobsBinding

    private lateinit var jPapiFunctions: JPapiFunctions
    private lateinit var jpViewModel: JPViewModel

    private lateinit var dialogFilter: DialogFilter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posted_jobs, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onResume() {
        super.onResume()

        jPapiFunctions.getPostedJobs(SSProfileData.mLoginData.userId.toString())
    }

    private fun initViews() {
        jpViewModel = ViewModelProvider(this)[JPViewModel::class.java]
        dialogFilter = DialogFilter(layoutInflater, requireContext(), ::onFilterSelected)

        jPapiFunctions = JPapiFunctions(
            requireContext(),
            lifecycleOwner = this,
            jpViewModel,
            onJobPostedResponse = {},
            ::onJobsFetched,
            onViewApplicationResponse = {},
            onGetAllJobs = {},
            onApplyJobs = {}
        )

        onClickListeners()
        editTextListener()
    }

    private var allJobsList = ArrayList<PostJobData>()

    private fun onJobsFetched(jobsList: ArrayList<PostJobData>) {
        allJobsList=jobsList
        initPostedJobs(jobsList)
    }


    private fun initPostedJobs(worksList: List<PostJobData>) {

        if (worksList.isEmpty()) {
            binding.emptyContent.visibility = View.VISIBLE
            binding.rvJobs.visibility = View.GONE
        } else {
            binding.emptyContent.visibility = View.GONE
            binding.rvJobs.visibility = View.VISIBLE
        }

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
        CallIntent.gotoViewPostedJobActivity(requireContext(), false, requireActivity())
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

            if (isFilteredList)
                initPostedJobs(filteredJobsList)
            else
                initPostedJobs(allJobsList)
        } else {
            binding.btFilter.setImageResource(R.drawable.filter_applied)
            filterList(city, district)
        }
    }

    private var filteredJobList = ArrayList<PostJobData>()

    private fun filterList(city: String, district: String) {
        filteredJobList.clear()

        var localAllJobList = ArrayList<PostJobData>()

        localAllJobList = if(isFilteredList)
            filteredJobsList
        else
            allJobsList

        if (city == "All Cities") {
            for (workItem in localAllJobList) {
                if (workItem.district == district) {
                    filteredJobList.add(workItem)
                }
            }
        } else {
            for (work in localAllJobList) {
                if (work.city == city && work.district == district) {
                    filteredJobList.add(work)
                }
            }
        }

        initPostedJobs(filteredJobList)
    }

    //Search

    private var isFilteredList = false

    private fun editTextListener() {

        binding.searchBar.doOnTextChanged { text, _, _, _ ->

            Log.e("Test", text.toString())

            if (text != null) {

                isFilteredList = if (text.isEmpty()) {
                    initPostedJobs(allJobsList)
                    false
                } else {
                    filterItems(text.toString())
                    true
                }
            }
        }

    }

    private var filteredJobsList: ArrayList<PostJobData> = ArrayList()

    private fun filterItems(text: String) {

        filteredJobsList.clear()

        for (localItems in allJobsList) {

            val extractedName = localItems.jobName!!.lowercase()

            if (extractedName.startsWith(text.lowercase())) {
                filteredJobsList.add(localItems)
            }
        }

        initPostedJobs(filteredJobsList)

    }

}