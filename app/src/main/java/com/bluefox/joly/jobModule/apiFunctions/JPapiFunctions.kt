package com.bluefox.joly.jobModule.apiFunctions

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.familylocation.mobiletracker.zCommonFuntions.UtilFunctions


class JPapiFunctions(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    mJpViewModel: JPViewModel,
    private val onJobPostedResponse: () -> Unit,
    private val onGetPostedJobsResponse: (postedJobsList: ArrayList<PostJobData>) -> Unit,
    private val onViewApplicationResponse: (applicationsList: ArrayList<LoginData>) -> Unit,
    private val onGetAllJobs: (postedJobsList: ArrayList<PostJobData>) -> Unit,
    private val onApplyJobs: () -> Unit,
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

    fun getPostedJobStatus(jobId: String, jobStatus: String) {
        jpViewModel.resetUpdateJobStatusResponse()
        jpViewModel.updateJobStatus(jobId, jobStatus)

        getPostedJobStatusObserver()
    }

    private fun getPostedJobStatusObserver() {
        jpViewModel.getUpdateJobStatusResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {
                UtilFunctions.showToast(mContext, "Job Status Updated SuccessFully")
                //TODO Impl Callback
            } else {
                UtilFunctions.showToast(mContext, "Server Not Responding")
            }
        }

    }


    fun getJobApplications(jobId: String) {
        jpViewModel.resetUpdateJobApplicationsResponse()
        jpViewModel.updateJobApplications(jobId)

        getJobApplicationsObserver()
    }

    private fun getJobApplicationsObserver() {
        jpViewModel.getUpdateJobApplicationsResponse().observe(mLifecycleOwner) {
            if (it.code == 200) {
                if (it.applicationsList.isNotEmpty())
                    onViewApplicationResponse.invoke(it.applicationsList)
                else {
                    UtilFunctions.showToast(mContext, "No Applications Received")
                }
//                UtilFunctions.showToast(mContext, "Job Application SuccessFully")
            } else {
                UtilFunctions.showToast(mContext, "Server Not Responding")
            }
        }

    }

    fun getAllJobs(jobId: String) {
        jpViewModel.resetGetAllJobsResponse()
        jpViewModel.getAllJobs(jobId)

        getAllJobsObserver()
    }

    private fun getAllJobsObserver() {
        jpViewModel.getAllJobsResponse().observe(mLifecycleOwner) {
            if (it.code == 200) {
                if (it.postedJobsList.isNotEmpty()) {

                    onGetAllJobs.invoke(it.postedJobsList)
                } else {
                    UtilFunctions.showToast(mContext, "No Jobs")
                }

            } else {
                UtilFunctions.showToast(mContext, "Server Not Responding")
            }
        }
    }

    fun jsApplyJobs(jobId: String, userId: String) {
        jpViewModel.resetJsApplyJobsResponse()
        jpViewModel.jsApplyJobs(jobId, userId)

        getApplyJobsObserver()
    }

    private fun getApplyJobsObserver() {
        jpViewModel.getJsApplyJobsResponse().observe(mLifecycleOwner) {
            if (it.status == 200) {
                onApplyJobs.invoke()
            } else {
                UtilFunctions.showToast(mContext, "Server Not Responding")
            }
        }
    }

}