package com.bluefox.joly.clientModule.postJob.apiFunctions

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.postJob.modalClass.CategoryItem
import com.bluefox.joly.clientModule.postJob.modalClass.JobItem
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions

class SSapiFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    ssViewModel: SSViewModel,
    private val onCategoriesResponse: (categoriesList: List<CategoryItem>) -> Unit,
    private val onJobsResponse: (jobsList: List<JobItem>) -> Unit,
    private val onWorkSubmitted: () -> Unit,
    private val onGetWorksResponse: (worksList: List<PostWorkData>) -> Unit
    ) {

    private var mContext: Context
    private var mLifecycleOwner: LifecycleOwner
    private var mssViewModel: SSViewModel

    init {
        mContext = context
        mLifecycleOwner = lifecycleOwner
        mssViewModel = ssViewModel
    }


    fun getCategories() {
        mssViewModel.resetCategories()
        mssViewModel.getCategories()
        categoriesObserver()
    }

    private fun categoriesObserver() {
        mssViewModel.getCategoriesResponse().observe(mLifecycleOwner)
        {
            if (it.status == 200) {
                if (it.data!!.isNotEmpty()) {
                    onCategoriesResponse.invoke(it.data as List<CategoryItem>)
                } else {
                    UtilFunctions.showToast(mContext, "No Categories Found")

                }
            } else {
                UtilFunctions.showToast(mContext, "Invalid Response")
            }
        }

    }

    fun getJobs() {
        mssViewModel.resetJobsResponse()
        mssViewModel.getJobs()
        getJobsResponse()
    }

    fun getJobsResponse() {
        mssViewModel.getJobsResponse().observe(mLifecycleOwner)
        {
            if (it.status == 200) {
                if (it.data!!.isNotEmpty()) {
                    onJobsResponse.invoke(it.data as List<JobItem>)
                } else {
                    UtilFunctions.showToast(mContext, "No Jobs Found")
                }
            } else {
                UtilFunctions.showToast(mContext, "Invalid Response")
            }
        }
    }

    fun postWork(postWorkData: PostWorkData) {
        mssViewModel.resetPostWorkResponse()
        mssViewModel.postWork(postWorkData)

        postWorkObserver()
    }

    private fun postWorkObserver() {
        mssViewModel.getPostWorkResponse().observe(mLifecycleOwner)
        {
            if (it.status == 200) {
                onWorkSubmitted.invoke()
            } else {
                UtilFunctions.showToast(mContext, "Invalid Response")

            }
        }
    }

    fun getSSWorks(mobileNo: String) {
        mssViewModel.resetGetSSWorkResponse()
        mssViewModel.getSSWork(mobileNo)
        getSSWorksObserver()
    }

    private fun getSSWorksObserver() {
        mssViewModel.getSSWorkResponse().observe(mLifecycleOwner) {

            if(it.status==200)
            {
                onGetWorksResponse.invoke(it.data as List<PostWorkData>)
            }else{
                UtilFunctions.showToast(mContext, "Invalid Response")
            }
        }
    }
}