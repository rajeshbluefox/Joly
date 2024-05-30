package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.SSProfileData
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.clientModule.login.modelClass.SSRegistrationDetailsData
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.postJob.modalClass.SSSelectedData
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.dummy.GetThemesResponse
import com.bluefox.joly.zSharedPreference.UserDetails
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject


class ApiHelperImplementation @Inject constructor(private val apiService: ApiInterface) :
    ApiHelper {
    override suspend fun getThemes(): GetThemesResponse {
        return apiService.getThemes()
    }

    override suspend fun validateLogin(loginData: LoginData): LoginResponse {

        if(SSProfileData.UserRole==1) {
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
        return apiService.postRegisterSS(
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.phoneNumber.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.name.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.dateOfBirth.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.gender.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.address.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.pinCode.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.pinCode.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.aadharNumber.toString()),
            RequestBody.create("text/plain".toMediaTypeOrNull(),sSRegistrationDetailsData.password.toString()),
            SSSelectedData.imagePart!!
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
            RequestBody.create("text/plain".toMediaTypeOrNull(), postWorkData.workDescription.toString()),
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
        return apiService.getSSWork(mobileNo)
    }


}