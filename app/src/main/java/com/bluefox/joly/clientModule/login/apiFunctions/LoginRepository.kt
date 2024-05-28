package com.bluefox.joly.clientModule.login.apiFunctions

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.dummy.GetThemesResponse
import com.bluefox.joly.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var loginResponse = LoginResponse()

    suspend fun validateLogin(loginData: LoginData): LoginResponse {
        try {
            withContext(Dispatchers.IO) {
                loginResponse = apiHelper.validateLogin(loginData)
            }
        } catch (_: Exception) {
        }
        return loginResponse
    }

}