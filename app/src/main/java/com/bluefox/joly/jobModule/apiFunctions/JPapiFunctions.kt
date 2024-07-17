package com.bluefox.joly.jobModule.apiFunctions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


class JPapiFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    mJpViewModel: JPViewModel,
    private val onJobPostedResponse: () -> Unit,
    private val onGetPostedJobsResponse: (postedJobsList: ArrayList<PostJobData>) -> Unit
) {

    private var mContext: Context = context
    private var mLifecycleOwner: LifecycleOwner = lifecycleOwner
    private var jpViewModel: JPViewModel = mJpViewModel

    fun postJob(postJobData: PostJobData) {
        jpViewModel.resetPostJobResponse()
        jpViewModel.postJob(postJobData)

        postJobObservers()
    }

    private fun postJobObservers() {
        jpViewModel.getPostJobResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {
                UtilFunctions.showToast(mContext, "Job Posted Successfully")
                onJobPostedResponse.invoke()
            }
        }
    }

    fun updateJob(postJobData: PostJobData) {
        jpViewModel.resetUpdateJobResponse()
        jpViewModel.updateJob(postJobData)

        updateJobObservers()
    }

    private fun updateJobObservers() {
        jpViewModel.getUpdateJobResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {
                UtilFunctions.showToast(mContext, "Job Updated Successfully")
            } else {
                UtilFunctions.showToast(mContext, "Server Not Responding")
            }
        }
    }

    fun getPostedJobs(userId: String) {
        jpViewModel.resetGetPostedJobsJP()
        jpViewModel.getPostedJobsJP(userId)

        getPostedJobsObserver()
    }

    private fun getPostedJobsObserver() {
        jpViewModel.getPostedJobsJPResponse().observe(mLifecycleOwner) {
            if (it.code == 200) {
                if (it.postedJobsList.isNotEmpty()) {
                    Log.e("Test", "1")
                    onGetPostedJobsResponse.invoke(it.postedJobsList)
                } else {
                    UtilFunctions.showToast(mContext, "No Jobs Posted")
                }
            }
        }

    }


}