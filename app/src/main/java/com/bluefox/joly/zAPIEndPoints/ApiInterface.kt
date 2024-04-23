package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.dummy.GetThemesResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("CallerThemes_get.php")
    suspend fun getThemes(): GetThemesResponse


}