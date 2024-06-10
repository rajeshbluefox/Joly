package com.bluefox.joly.serviceProviderModule

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluefox.joly.clientModule.postJob.adapters.CategorySpinnerAdapter
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.databinding.ActivityAddServiceBinding
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPAPIFunctions
import com.bluefox.joly.serviceProviderModule.apiFunctions.SPViewModel
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServicesSelectedJobs
import com.bluefox.joly.serviceProviderModule.supportFunctions.SelectJobAdapter
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bluefox.joly.zSharedPreference.UserDetails
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddServiceBinding

    private lateinit var sPViewModel: SPViewModel
    private lateinit var sPAPIFunctions: SPAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources, binding.appBarLt)

        if (AddServicesSelectedJobs.selectedPage == 1) {

            binding.tvAppBarTitle.text = "Add Service"

            initViews()
            initAPIFunctions()
        } else {

            binding.tvAppBarTitle.text = "View Service"
            binding.tvSelectCategory.text = "Category"
            binding.tvPriceRange.text = "Price Range"
            binding.tvSelectJob.text = "Jobs Added"

            setSelectedService()
        }

        onClickListeners()
    }

    fun initAPIFunctions() {
        AddServicesSelectedJobs.reset()

        sPViewModel = ViewModelProvider(this)[SPViewModel::class.java]
        sPAPIFunctions = SPAPIFunctions(
            this,
            this,
            sPViewModel,
            onGetServicesResponse = {},
            onTestimonyPostedResponse = {},
            ::onServiceAdded,
            onGetTestimonials = {}
        )
    }

    fun onServiceAdded() {
        Log.e("Test", "Service Added")
        finish()
    }


    private fun initViews() {

        if (ServicesCatJob.isDataFetched) {
            filterCategories()
        } else {
            UtilFunctions.showToast(this, "Categories Not Found")
            disableEditing()
        }
    }

    fun filterCategories() {
        val servicesOffered = AddServicesSelectedJobs.providingCategories
//        AddServicesSelectedJobs.filteredCategoriesList = ServicesCatJob.categoriesList
        initializeFilteredCategoriesList()

        for (serviceItem in servicesOffered) {
            val categoryItem =
                ServicesCatJob.categoriesList.find { it.categoryID == serviceItem.categoryId }

            // Remove item only from filteredCategoriesList
            if (categoryItem != null) {
                AddServicesSelectedJobs.filteredCategoriesList.removeIf { it.categoryID == categoryItem.categoryID }
            }

//            AddServicesSelectedJobs.filteredCategoriesList.remove(categoryItem)
        }

        if (AddServicesSelectedJobs.filteredCategoriesList.isNotEmpty())
            initCategoriesSpinner(AddServicesSelectedJobs.filteredCategoriesList)
        else
            disableEditing()
    }

    fun initializeFilteredCategoriesList() {
        AddServicesSelectedJobs.filteredCategoriesList = ServicesCatJob.categoriesList.map { it.copy() } as ArrayList<CategoryItem>
    }



    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btSubmit.setOnClickListener {
            Log.e("Test", "Selected Job ${AddServicesSelectedJobs.selectedJobsList}")

            checkValues()
        }
    }

    private fun checkValues() {
        val nMinPrice = binding.etMinPrice.text.toString()
        val nMaxPrice = binding.etMaxPrice.text.toString()

        if (nMinPrice.isEmpty()) {
            UtilFunctions.showToast(this, "Enter Minimum Price")
            return
        }

        if (nMaxPrice.isEmpty()) {
            UtilFunctions.showToast(this, "Enter Maximum Price")
            return
        }

        if (AddServicesSelectedJobs.selectedJobsList.isEmpty()) {
            UtilFunctions.showToast(this, "Select Jobs")
            return
        }

        val addServiceData = AddServiceData()
        addServiceData.phoneNumber = UserDetails.getUserMobileNo(this)
        addServiceData.categoryId = AddServicesSelectedJobs.categoryId.toString()
        addServiceData.jobId = AddServicesSelectedJobs.selectedJobsList.toString()
        addServiceData.priceRange = "$nMinPrice - $nMaxPrice"
        addServiceData.status = "1"

        sPAPIFunctions.addService(addServiceData)
    }

    private fun initCategoriesSpinner(
        categoryList: List<CategoryItem>
    ) {

        Log.e("Test","CatListSpinner Size ${ServicesCatJob.categoriesList}")


        val newItem = CategoryItem(
            null,
            "Select",

            )

        val newAcList = listOf(newItem) + categoryList

        val adapter = CategorySpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categoryList
        )

        binding.spSelectCategory.adapter = adapter
        binding.spSelectCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    SSSelectedData.categoryItem = categoryList[position]

                    if(AddServicesSelectedJobs.selectedPage==1)
                        filterJob(SSSelectedData.categoryItem.categoryID!!)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }
    }

    fun filterJob(categoryId: Int) {
        AddServicesSelectedJobs.categoryId = categoryId

        val jobList = ServicesCatJob.jobList
        val filteredList = jobList.filter { it.categoryId == categoryId }

        initJobAdapter(filteredList)
    }

    private fun initJobAdapter(jobList: List<JobItem>) {

        Log.e("Test","Setting Adapter ${jobList.size}")

        val selectJobAdapter = SelectJobAdapter(this, jobList)
        binding.rvJob.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = selectJobAdapter
        }
    }

    ///View Service


    fun setSelectedService() {
        val categoryItem =
            ServicesCatJob.categoriesList.find { it.categoryID == AddServicesSelectedJobs.selService.categoryId }



        disableEditing()

        if(categoryItem!=null) {
            val oneCategoryList: List<CategoryItem>
            oneCategoryList = listOf(categoryItem!!)
            initCategoriesSpinner(oneCategoryList)
        }else{
            UtilFunctions.showToast(this,"Cat Empty")
        }

        Log.e("Test","Sel ${AddServicesSelectedJobs.selService}")
        Log.e("Test","CatList Size ${ServicesCatJob.categoriesList}")



        //Setting Price
        val range = parseRange(AddServicesSelectedJobs.selService.priceRange!!)

        if (range != null) {
            val (start, end) = range

            binding.etMinPrice.setText("$start")
            binding.etMaxPrice.setText("$end")



            println("Start: $start, End: $end") // Output: Start: 100, End: 2000
        } else {
            println("Invalid range format")
        }

        //Setting Jobs
        val jobIdList = stringToIntList(AddServicesSelectedJobs.selService.jobId!!)
        Log.e("Test","Jobs - $jobIdList")

        val jobsList = ArrayList<JobItem>()

        for (jobId in jobIdList) {
            val jobItem = ServicesCatJob.jobList.find { it.jobId == jobId }
            jobsList.add(jobItem!!)
        }

        Log.e("Test","JobsFiltered - $jobsList")

        initJobAdapter(jobsList)

    }

    fun disableEditing() {
        binding.spSelectCategory.isEnabled = false

        binding.etMinPrice.isEnabled = false
        binding.etMaxPrice.isEnabled = false

        //Disable Save button
        binding.btSubmit.visibility = View.GONE
    }

    fun parseRange(range: String): Pair<Int, Int>? {
        return try {
            // Split the string by " - "
            val parts = range.split(" - ")
            // Convert the parts to integers and return as a Pair
            val start = parts[0].trim().toInt()
            val end = parts[1].trim().toInt()
            Pair(start, end)
        } catch (e: Exception) {
            // Return null if the input format is invalid
            null
        }
    }


    fun stringToIntList(input: String): List<Int> {
        // Remove the square brackets and split the string by commas
        return input.removeSurrounding("[", "]")
            .split(",")
            .map { it.trim().toInt() }
    }

}