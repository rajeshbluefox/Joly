package com.bluefox.joly.dummy

import com.bluefox.joly.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CallThemesRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var getThemesResponse = GetThemesResponse()

    suspend fun getThemes(): GetThemesResponse {
        try {
            withContext(Dispatchers.IO) {
                getThemesResponse = apiHelper.getThemes()
            }
        } catch (_: Exception) {
        }
        return getThemesResponse
    }

}