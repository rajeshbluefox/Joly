package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.login.modelClass.RegistrationResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import com.bluefox.joly.dummy.GetThemesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiInterface {

    @GET("CallerThemes_get.php")
    suspend fun getThemes(): GetThemesResponse

    @FormUrlEncoded
    @POST("service_seeker_login.php")
    suspend fun validateLogin(
        @Field("MobileNo") phoneNumber: String,
        @Field("Password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("service_provider_login.php")
    suspend fun validateLoginSP(
        @Field("MobileNumber") phoneNumber: String,
        @Field("Password") password: String
    ): LoginResponse

    @GET("ServiceProvider_Categories_get.php")
    suspend fun getCategories(): GetCategoriesResponse

    @GET("ServiceProvider_Jobs_get.php")
    suspend fun getJobs(): GetJobsResponse



    @Multipart
    @POST("service_seeker_inser.php")
    suspend fun postSSWork(
        @Part("phone_number") phoneNumber: RequestBody,
        @Part("WorkName") WorkName: RequestBody,
        @Part("Description") WorkDescription: RequestBody,
        @Part("CategoryID") CategoryID: RequestBody,
        @Part("JobTypeID") JobTypeID: RequestBody,
        @Part("AreaID") AreaID: RequestBody,
        @Part("Wage") Wage: RequestBody,
        @Part photos: List<MultipartBody.Part>
    ): PostWorkResponse

//    @Part photos: MultipartBody.Part
//    @Part workImages: MultipartBody.Part

    @Multipart
    @POST("job_provider_insert.php")
    suspend fun postRegisterSS(
        @Part("MobileNo") mobileNo: RequestBody,
        @Part("Name") name: RequestBody,
        @Part("Age") age: RequestBody,
        @Part("Gender") gender: RequestBody,
        @Part("Address") address: RequestBody,
        @Part("Pincode") pincode: RequestBody,
        @Part("Location") location: RequestBody,
        @Part("AadharNumber") aadharNumber: RequestBody,
        @Part("Password") password: RequestBody,
        @Part photos: MultipartBody.Part
    ): RegistrationResponse

    @Multipart
    @POST("service_provider_insert.php")
    suspend fun postRegisterSP(
        @Part("MobileNumber") mobileNo: RequestBody,
        @Part("CompanyName") name: RequestBody,
        @Part("Age") age: RequestBody,
        @Part("Gender") gender: RequestBody,
        @Part("Address") address: RequestBody,
        @Part("PinCode") pincode: RequestBody,
        @Part("Location") location: RequestBody,
        @Part("City") city: RequestBody,
        @Part("State") State: RequestBody,
        @Part("AadharNumber") aadharNumber: RequestBody,
        @Part("Password") password: RequestBody,
        @Part("Qualification") qualification: RequestBody,
        @Part("PreviousExperience") previousExperience: RequestBody,
        @Part("Description") description: RequestBody,
        @Part("PortfolioLink") portfolioLink: RequestBody,
        @Part photos: MultipartBody.Part
    ): RegistrationResponse


    @FormUrlEncoded
    @POST("service_seeker_get.php")
    suspend fun getSSWork(
        @Field("phone_number") phoneNumber: String
    ): GetWorkResponse


    @FormUrlEncoded
    @POST("ServiceProvider_Testimonials_insert.php")
    suspend fun postSPTestimony(
        @Field("PhoneNumber") phoneNumber: String,
        @Field("Testimony") testimony: String,
        @Field("Status") status: String

    ): SPTestimonyResponse
}