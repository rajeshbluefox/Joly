package com.bluefox.joly.clientModule.postJob.apiFunctions

import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.clientModule.viewServices.modelClass.CheckFBStatusResponse
import com.bluefox.joly.clientModule.viewServices.modelClass.GetServiceProvidersResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import com.bluefox.joly.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SSRepository @Inject constructor(
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

    private var getJobsResponse = GetJobsResponse()

    suspend fun getJobs(): GetJobsResponse {
        try {
            withContext(Dispatchers.IO) {
                getJobsResponse = apiHelper.getJobs()
            }
        } catch (_: Exception) {
        }
        return getJobsResponse
    }

    private var postWorkResponse = PostWorkResponse()

    suspend fun postWork(postWorkData: PostWorkData): PostWorkResponse {
        try {
            withContext(Dispatchers.IO) {
                postWorkResponse = apiHelper.postWorkData(postWorkData)
            }
        } catch (_: Exception) {
        }
        return postWorkResponse
    }

    private var getWorkResponse = GetWorkResponse()

    suspend fun getSSWorks(mobileNo: String): GetWorkResponse {
        try {
            withContext(Dispatchers.IO) {
                getWorkResponse = apiHelper.getSSWorks(mobileNo)
            }
        } catch (_: Exception) {
        }
        return getWorkResponse
    }

    private var getCloseWorkResponse = SPTestimonyResponse()

    suspend fun getCloseWork(workId: String,closingFeedback: Int): SPTestimonyResponse {
        try {
            withContext(Dispatchers.IO) {
                getCloseWorkResponse = apiHelper.ssCloseWork(workId,closingFeedback)
            }
        } catch (_: Exception) {
        }
        return getCloseWorkResponse
    }

    private var checkFBResponse  = CheckFBStatusResponse()

    suspend fun checkFbStatus(spId: Int,fpId: Int): CheckFBStatusResponse {
        try {
            withContext(Dispatchers.IO) {
                checkFBResponse = apiHelper.ssCheckFBStatus(spId, fpId)
            }
        } catch (_: Exception) {
        }
        return checkFBResponse
    }

    private var getServiceProvidersResponse  = GetServiceProvidersResponse()

    suspend fun getServiceProvidersResponse(categoryId: Int): GetServiceProvidersResponse {
        try {
            withContext(Dispatchers.IO) {
                getServiceProvidersResponse = apiHelper.getServiceProviders(categoryId)
            }
        } catch (_: Exception) {
        }
        return getServiceProvidersResponse
    }
}