package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.dummy.GetThemesResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetPostedJobsResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobResponse
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceResponse
import com.bluefox.joly.serviceProviderModule.modelClass.GetTestimoniesResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SpOfferedServiceResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject


class ApiHelperImplementation @Inject constructor(private val apiService: ApiInterface) :
    ApiHelper {
    override suspend fun getThemes(): GetThemesResponse {
        return apiService.getThemes()
    }

    override suspend fun validateLogin(loginData: LoginData): LoginResponse {

        if(SSProfileData.UserRole == 3)
        {
            return apiService.validateLoginJP(
                loginData.phoneNumber!!,
                loginData.password!!
            )
        }

        if (SSProfileData.UserRole == 1) {
            return apiService.validateLogin(
                loginData.phoneNumber!!,
                loginData.password!!
            )
        }

        return apiService.validateLoginSP(
            loginData.phoneNumber!!,
            loginData.password!!
        )
    }

    override suspend fun ssRegister(sSRegistrationDetailsData: SSRegistrationDetailsData): RegistrationResponse {

        if(SSProfileData.UserRole == 3)
        {
            return apiService.postRegisterJP(
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.phoneNumber.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.companyName.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.dateOfBirth.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.gender.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.address.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.pinCode.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.address.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.city.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.state.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.state.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.aadharNumber.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.password.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.description.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.portfolioLink.toString()
                ),
                SSSelectedData.registerPhoto!!

            )
        }


        if (SSProfileData.UserRole == 1) {
            return apiService.postRegisterSS(
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.phoneNumber.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.name.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.dateOfBirth.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.gender.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.address.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.pinCode.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.pinCode.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.aadharNumber.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.password.toString()
                ),
                SSSelectedData.registerPhoto!!
            )
        }

        return apiService.postRegisterSP(
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.phoneNumber.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.name.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.dateOfBirth.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.gender.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.address.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.pinCode.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.location.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.city.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.state.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.aadharNumber.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.password.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.qualification.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.previousExperience.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.description.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.portfolioLink.toString()
            ),
            SSSelectedData.registerPhoto!!
        )
    }

    override suspend fun getSPTestimonies(mobileNo: String): GetTestimoniesResponse {
        return apiService.getSPTestimonies(mobileNo)
    }

    override suspend fun postSPTestimony(spTestimonyData: SPTestimonyData): SPTestimonyResponse {
        return apiService.postSPTestimony(
            spTestimonyData.customerName!!,
            spTestimonyData.phoneNumber!!,
            spTestimonyData.testimony!!,
            spTestimonyData.status!!
        )
    }

    override suspend fun getCategories(): GetCategoriesResponse {
        return apiService.getCategories()
    }

    override suspend fun getJobs(): GetJobsResponse {
        return apiService.getJobs()
    }

    override suspend fun postWorkData(postWorkData: PostWorkData): PostWorkResponse {
        return apiService.postSSWork(
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.phoneNumber.toString()
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.workName.toString()),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.workDescription.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.categoryId.toString()
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.jobId.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.areaId.toString()),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.wageOffered.toString()
            ),
            SSSelectedData.parts
        )
    }

    override suspend fun getSSWorks(mobileNo: String): GetWorkResponse {
        if(SSProfileData.UserRole==1)
        return apiService.getSSWork(mobileNo)

        return apiService.getSSWorkByCategories(mobileNo)
    }

    override suspend fun getServiceOfferedSP(mobileNo: String): SpOfferedServiceResponse {
        return apiService.getServiceOfferedSP(mobileNo)
    }

    override suspend fun addServiceSP(addServiceData: AddServiceData): AddServiceResponse {
        return apiService.addSPService(
            addServiceData.phoneNumber!!,
            addServiceData.categoryId!!,
            addServiceData.jobId!!,
            addServiceData.priceRange!!,
            addServiceData.status!!
        )
    }

    //Job Provider

    override suspend fun jpPostJob(postJobData: PostJobData): PostJobResponse {
        return apiService.jp_PostJob(
            postJobData.phoneNumber!!,
            postJobData.jobName!!,
            postJobData.jobDetails!!,
            postJobData.jobDescription!!,
            postJobData.eligibility!!,
            postJobData.deadLineToApply!!,
            postJobData.skills!!
        )
    }

    override suspend fun jpGetPostedJob(userId: String): GetPostedJobsResponse {
        return apiService.jp_getPostedJob(userId)
    }

}