package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.dummy.GetThemesResponse
import javax.inject.Inject


class ApiHelperImplementation @Inject constructor(private val apiService: ApiInterface) :
    ApiHelper {
    override suspend fun getThemes(): GetThemesResponse {
        return apiService.getThemes()
    }

    override suspend fun validateLogin(loginData: LoginData): LoginResponse {
        return apiService.validateLogin(
            loginData.phoneNumber!!,
            loginData.password!!
        )
    }


}