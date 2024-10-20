package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.clientModule.viewJob.modalClass.SSSelected
import com.bluefox.joly.clientModule.viewServices.modelClass.CheckFBStatusResponse
import com.bluefox.joly.clientModule.viewServices.modelClass.GetServiceProvidersResponse
import com.bluefox.joly.dummy.GetThemesResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetApplicationResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetPostedJobsResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.SelJobDetails
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceResponse
import com.bluefox.joly.serviceProviderModule.modelClass.GetTestimoniesResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
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

        if (SSProfileData.UserRole == 4) {
            return apiService.jsLogin(
                loginData.phoneNumber!!,
                loginData.password!!
            )
        }

        if (SSProfileData.UserRole == 3) {
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


        if (SSProfileData.UserRole == 4) {
            return apiService.postRegisterJobSeeker(
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
                    sSRegistrationDetailsData.password.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.aadharNumber.toString()
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
                    sSRegistrationDetailsData.country.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.qualification.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.skills.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.previousExperience.toString()
                ),
                SSSelectedData.registerPhoto!!
            )
        }


        if (SSProfileData.UserRole == 3) {
            return apiService.postRegisterJP(
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
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.companyContact.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.companyMail.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    sSRegistrationDetailsData.companyGSTNO.toString()
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
                    sSRegistrationDetailsData.alternativeNumber.toString()
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
                sSRegistrationDetailsData.alternativeNumber.toString()
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
                sSRegistrationDetailsData.workingHours.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                sSRegistrationDetailsData.portfolioLink.toString()
            ),
            SSSelectedData.registerPhoto!!
        )
    }

    override suspend fun ssCloseWork(workId: String,closingFeedback: Int): SPTestimonyResponse {
        return apiService.ssCloseWork(workId,closingFeedback)
    }

    override suspend fun ssCheckFBStatus(spId: Int, fpId: Int): CheckFBStatusResponse {
        return apiService.checkFBStatus(spId, fpId,SSSelected.workData.workId!!.toInt())
    }

    override suspend fun getServiceProviders(categoryId: Int): GetServiceProvidersResponse {
        return apiService.getServiceProviders(categoryId)
    }

    override suspend fun getSPTestimonies(mobileNo: String): GetTestimoniesResponse {
        return apiService.getSPTestimonies(mobileNo)
    }

    override suspend fun postSPTestimony(spTestimonyData: SPTestimonyData): SPTestimonyResponse {
        return apiService.postSPTestimony(
            spTestimonyData.customerName!!,
            spTestimonyData.phoneNumber!!,
            spTestimonyData.testimony!!,
            spTestimonyData.status!!,
            spTestimonyData.serviceProviderId,
            spTestimonyData.rating,
            spTestimonyData.feedbackProviderId,
            SSSelected.workData.workId!!.toInt()
        )
    }

    override suspend fun getCategories(): GetCategoriesResponse {
        return apiService.getCategories()
    }

    override suspend fun getJobs(): GetJobsResponse {
        return apiService.getJobs()
    }

    override suspend fun postWorkData(postWorkData: PostWorkData): PostWorkResponse {
        if (postWorkData.isAudioAttached)
            return apiService.postSSWork(
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.phoneNumber.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.workName.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.workDescription.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.categoryId.toString()
                ),
                RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.jobId.toString()),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.areaId.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.district.toString()
                ),
                RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.city.toString()),

                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.wageOffered.toString()
                ),
                RequestBody.create(
                    "text/plain".toMediaTypeOrNull(),
                    postWorkData.deadlineTime.toString()
                ),
                SSSelectedData.auido!!,
                SSSelectedData.parts
            )

        return apiService.postSSWorkNoAudio(
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.phoneNumber.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.workName.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.workDescription.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.categoryId.toString()
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.jobId.toString()),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.areaId.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.district.toString()
            ),
            RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.city.toString()),

            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.wageOffered.toString()
            ),
            RequestBody.create(
                "text/plain".toMediaTypeOrNull(),
                postWorkData.deadlineTime.toString()
            ),
            SSSelectedData.parts
        )
    }

    override suspend fun getSSWorks(mobileNo: String): GetWorkResponse {
        if (SSProfileData.UserRole == 1)
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
            postJobData.userId.toString(),
            postJobData.jobName!!,
            postJobData.jobDetails!!,
            postJobData.jobDescription!!,
            postJobData.eligibility!!,
            postJobData.deadLineToApply!!,
            postJobData.skills!!,
            postJobData.city!!,
            postJobData.district!!
        )
    }

    override suspend fun jpUpdatePostedJob(postJobData: PostJobData): PostJobResponse {
        return apiService.jp_UpdateJob(
            postJobData.jobId.toString(),
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

    override suspend fun jpUpdateJobStatus(jobId: String, jobStatus: String): PostJobResponse {
        return apiService.jp_UpdateJobStatus(jobId, jobStatus)
    }

    override suspend fun jpGetJobApplications(jobId: String): GetApplicationResponse {
        return apiService.jp_getPostedApplications(jobId)
    }

    override suspend fun jsGetAllJobs(userId: String): GetPostedJobsResponse {
        if (SelJobDetails.selFragmentJobSeeker == 0)
            return apiService.getAllJobs(userId)

        return apiService.jsAppliedJobs(userId)
    }

    override suspend fun jsApplyJob(jobId: String, userId: String): PostJobResponse {
        return apiService.jsApplyJob(jobId, userId)
    }


}