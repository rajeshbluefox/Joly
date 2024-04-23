package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.dummy.GetThemesResponse
import javax.inject.Inject


class ApiHelperImplementation @Inject constructor(private val apiService: ApiInterface) :
    ApiHelper {
    override suspend fun getThemes(): GetThemesResponse {
        return apiService.getThemes()
    }


}