package com.bluefox.joly.serviceProviderModule.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluefox.joly.clientModule.login.apiFunctions.LoginRepository
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SPViewModel @Inject constructor(
    private val sPRepository: SPRepository
) : ViewModel() {

    private var sPTestimonyResponse = MutableLiveData<SPTestimonyResponse>()

    fun postSPTestimony(sPTestimonyData: SPTestimonyData) {
        viewModelScope.launch {
            sPTestimonyResponse.postValue(
                sPRepository.postSPTestimony(sPTestimonyData)
            )
        }
    }

    fun resetSPTestimonyResponse() {
        sPTestimonyResponse = MutableLiveData<SPTestimonyResponse>()
    }

    fun getSPTestimonyResponse(): LiveData<SPTestimonyResponse> {
        return sPTestimonyResponse
    }


}