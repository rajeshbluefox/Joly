package com.bluefox.joly.clientModule.login.modelClass

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: LoginData? = null
)