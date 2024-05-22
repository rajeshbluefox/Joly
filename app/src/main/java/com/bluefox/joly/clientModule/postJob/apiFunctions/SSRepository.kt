package com.bluefox.joly.clientModule.postJob.apiFunctions

import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
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

}