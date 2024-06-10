package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.dummy.GetThemesResponse
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceResponse
import com.bluefox.joly.serviceProviderModule.modelClass.GetTestimoniesResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SpOfferedServiceResponse


interface ApiHelper {

    suspend fun getThemes(): GetThemesResponse

    suspend fun validateLogin(loginData: LoginData): LoginResponse
    suspend fun ssRegister(sSRegistrationDetailsData : SSRegistrationDetailsData): RegistrationResponse

    //Testimonies
    suspend fun getSPTestimonies(mobileNo: String): GetTestimoniesResponse

    suspend fun postSPTestimony(spTestimonyData: SPTestimonyData): SPTestimonyResponse

    suspend fun getCategories(): GetCategoriesResponse

    suspend fun getJobs(): GetJobsResponse

    suspend fun postWorkData(postWorkData: PostWorkData): PostWorkResponse

    suspend fun getSSWorks(mobileNo: String): GetWorkResponse

    suspend fun getServiceOfferedSP(mobileNo: String): SpOfferedServiceResponse

    suspend fun addServiceSP(addServiceData: AddServiceData): AddServiceResponse

}