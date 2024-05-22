package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
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

    @GET("ServiceProvider_Categories_get.php")
    suspend fun getCategories(): GetCategoriesResponse

    @GET("ServiceProvider_Jobs_get.php")
    suspend fun getJobs(): GetJobsResponse



    @Multipart
    @POST("Products_insert.php")
    suspend fun postSSWork(
        @Part("phone_number") phoneNumber: RequestBody,
        @Part("WorkName") WorkName: RequestBody,
        @Part("CategoryID") CategoryID: RequestBody,
        @Part("JobTypeID") JobTypeID: RequestBody,
        @Part("AreaID") AreaID: RequestBody,
        @Part("Wage") Wage: RequestBody
    ): PostWorkResponse

//    @Part workImages: MultipartBody.Part
}