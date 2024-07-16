package com.bluefox.joly.jobModule.apiFunctions

import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
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

}