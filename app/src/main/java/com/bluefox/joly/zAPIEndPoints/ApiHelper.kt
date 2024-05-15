package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.dummy.GetThemesResponse


interface ApiHelper {

    suspend fun getThemes(): GetThemesResponse

    suspend fun validateLogin(loginData: LoginData): LoginResponse


}