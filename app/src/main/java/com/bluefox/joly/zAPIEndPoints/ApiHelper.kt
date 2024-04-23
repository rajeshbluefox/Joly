package com.bluefox.joly.zAPIEndPoints

import com.bluefox.joly.dummy.GetThemesResponse


interface ApiHelper {

    suspend fun getThemes(): GetThemesResponse



}