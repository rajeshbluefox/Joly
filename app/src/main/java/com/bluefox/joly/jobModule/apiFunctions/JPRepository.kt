package com.bluefox.joly.jobModule.apiFunctions

import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetApplicationResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetPostedJobsResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobResponse
import com.bluefox.joly.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class JPRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var getCategoriesResponse = GetCategoriesResponse()

    suspend fun getCategories(): GetCategoriesResponse {
        try {
            withContext(Dispatchers.IO) {
                getCategoriesResponse = apiHelper.getCategories()
            }
        } catch (_: Exception) {
        }
        return getCategoriesResponse
    }

    private var postJobResponse = PostJobResponse()

    suspend fun getPostJobResponse(postJobData: PostJobData): PostJobResponse {
        try {
            withContext(Dispatchers.IO) {
                postJobResponse = apiHelper.jpPostJob(postJobData)
            }
        } catch (_: Exception) {
        }
        return postJobResponse
    }

    private var postJobResponseUJ = PostJobResponse()

    suspend fun getUpdateResponse(postJobData: PostJobData): PostJobResponse {
        try {
            withContext(Dispatchers.IO) {
                postJobResponseUJ = apiHelper.jpUpdatePostedJob(postJobData)
            }
        } catch (_: Exception) {
        }
        return postJobResponseUJ
    }

    private var getPostedJobsResponse = GetPostedJobsResponse()

    suspend fun getPostedJobsJP(userId: String): GetPostedJobsResponse {
        try {
            withContext(Dispatchers.IO) {
                getPostedJobsResponse = apiHelper.jpGetPostedJob(userId)
            }
        } catch (_: Exception) {
        }
        return getPostedJobsResponse
    }

    private var getPostedJobsResponseUJS = PostJobResponse()

    suspend fun getUpdatedJobStatusResponse(jobId: String, jobStatus: String): PostJobResponse {
        try {
            withContext(Dispatchers.IO) {
                getPostedJobsResponseUJS = apiHelper.jpUpdateJobStatus(jobId,jobStatus)
            }
        } catch (_: Exception) {
        }
        return getPostedJobsResponseUJS
    }


    private var getJobApplicationsResponse = GetApplicationResponse()

    suspend fun getPostedApplicationsResponse(jobId: String): GetApplicationResponse {
        try {
            withContext(Dispatchers.IO) {
                getJobApplicationsResponse = apiHelper.jpGetJobApplications(jobId)
            }
        } catch (_: Exception) {
        }
        return getJobApplicationsResponse
    }

    private var getPostedJobsResponseJS = GetPostedJobsResponse()

    suspend fun getAllJobs(userId: String): GetPostedJobsResponse {
        try {
            withContext(Dispatchers.IO) {
                getPostedJobsResponseJS = apiHelper.jsGetAllJobs(userId)
            }
        } catch (_: Exception) {
        }
        return getPostedJobsResponseJS
    }

    private var getAppliedJobResponse = PostJobResponse()

    suspend fun jsApplyJob(jobId: String,userId: String): PostJobResponse {
        try {
            withContext(Dispatchers.IO) {
                getAppliedJobResponse = apiHelper.jsApplyJob(jobId ,userId)
            }
        } catch (_: Exception) {
        }
        return getAppliedJobResponse
    }

}