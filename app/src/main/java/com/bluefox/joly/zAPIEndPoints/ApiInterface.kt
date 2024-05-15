package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.dummy.GetThemesResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("CallerThemes_get.php")
    suspend fun getThemes(): GetThemesResponse

    @FormUrlEncoded
    @POST("service_seeker_login.php")
    suspend fun validateLogin(
        @Field("MobileNo") phoneNumber: String,
        @Field("Password") password: String
    ): LoginResponse


}