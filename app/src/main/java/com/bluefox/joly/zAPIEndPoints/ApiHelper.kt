package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.dummy.GetThemesResponse


interface ApiHelper {

    suspend fun getThemes(): GetThemesResponse

    suspend fun validateLogin(loginData: LoginData): LoginResponse
    suspend fun ssRegister(sSRegistrationDetailsData : SSRegistrationDetailsData): RegistrationResponse

    suspend fun getCategories(): GetCategoriesResponse

    suspend fun getJobs(): GetJobsResponse

    suspend fun postWorkData(postWorkData: PostWorkData): PostWorkResponse

    suspend fun getSSWorks(mobileNo: String): GetWorkResponse

}