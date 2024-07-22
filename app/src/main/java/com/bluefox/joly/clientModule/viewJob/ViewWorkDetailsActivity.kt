package com.bluefox.joly.clientModule.viewJob

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSViewModel
import com.bluefox.joly.clientModule.postJob.apiFunctions.SSapiFunctions
import com.bluefox.joly.clientModule.postJob.modalClass.ServicesCatJob
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewJob.supportFunctions.ViewJobsUI
import com.bluefox.joly.databinding.ActivityViewJobDetailsBinding
import com.bluefox.joly.zCommonFunctions.StatusBarUtils
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class ViewWorkDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewJobDetailsBinding

    private lateinit var sSapiFunctions: SSapiFunctions
    private lateinit var ssViewModel: SSViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        StatusBarUtils.transparentStatusBarWhite(this)
        StatusBarUtils.setTopPadding(resources,binding.appBarLt)

        initViews()
        setDetails()
        onClickListeners()
    }

    private fun initViews() {
        ssViewModel = ViewModelProvider(this)[SSViewModel::class.java]

        sSapiFunctions = SSapiFunctions(
            this,
            this,
            ssViewModel,
            onCategoriesResponse={},
            onJobsResponse={},
            onWorkSubmitted = {},
            onGetWorksResponse={},
            ::onServiceClosed
        )

    }

    private fun onClickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btCloseService.setOnClickListener {
            sSapiFunctions.getSSCloseWork(SSSelected.workData.workId.toString())

        }
    }



    private fun setDetails() {
//        binding.tvJobTitle.text=JobSelected.jobsData.jobName
        loadSLideShowImages()

        Glide.with(this)
            .load(SSSelected.workData.profilePhoto)
            .fitCenter()
            .into(binding.profileImage)

        binding.tvAppBarTitle.text=SSSelected.workData.workName

        binding.tvWageOfferedValue.text="₹ ${SSSelected.workData.wageOffered}"
//        binding.tvJobLocation.text=SSSelected.workData.areaId.toString()

        binding.tvDescription.text=SSSelected.workData.workDescription

        //Set Category
        val categoryName = ServicesCatJob.categoriesList.find { it.categoryID == SSSelected.workData.categoryId }
        binding.tvCategoryValue.text=categoryName?.categoryName

        //Set Job
        val jobItem = ServicesCatJob.jobList.find { it.jobId == SSSelected.workData.jobId }
        binding.tvJobTypeValue.text=jobItem?.jobName

        binding.tvDateValue.text=UtilFunctions.formatDate(SSSelected.workData.workPostedDate!!)

        if(SSProfileData.UserRole==1)
            hideClientDetailsLt()
        else{
            binding.btCloseService.visibility=View.GONE

            binding.tvClientName.text=SSSelected.workData.name
            binding.tvClientNumber.text=SSSelected.workData.phoneNumber
        }
    }

    private fun hideClientDetailsLt()
    {
        binding.clientDetaiilsLt.visibility=View.GONE
    }

    private fun loadSLideShowImages()
    {
        val imageListAdapter = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        val imagesList = SSSelected.workData.data!!

        for(image in imagesList)
        {
            imageListAdapter.add(SlideModel(image!!.url))
        }

        binding.imageSlider.setImageList(imageListAdapter)
        binding.imageSlider.setImageList(imageListAdapter, ScaleTypes.FIT) // for all images

    }

    fun onServiceClosed()
    {
        finish()
    }


}