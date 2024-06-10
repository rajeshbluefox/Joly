package com.bluefox.joly.serviceProviderModule.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluefox.joly.clientModule.login.apiFunctions.LoginRepository
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceData
import com.bluefox.joly.serviceProviderModule.modelClass.AddServiceResponse
import com.bluefox.joly.serviceProviderModule.modelClass.GetTestimoniesResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyData
import com.bluefox.joly.serviceProviderModule.modelClass.SPTestimonyResponse
import com.bluefox.joly.serviceProviderModule.modelClass.SpOfferedServiceResponse
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

    private var spOfferedServiceResponse = MutableLiveData<SpOfferedServiceResponse>()

    fun getServiceOfferedSP(mobileNo: String) {
        viewModelScope.launch {
            spOfferedServiceResponse.postValue(
                sPRepository.getServiceOfferedSP(mobileNo)
            )
        }
    }

    fun resetGetServiceOfferedSPResponse() {
        spOfferedServiceResponse = MutableLiveData<SpOfferedServiceResponse>()
    }

    fun getServiceOfferedSPResponse(): LiveData<SpOfferedServiceResponse> {
        return spOfferedServiceResponse
    }


    //Add Service SP
    private var addServiceResponse = MutableLiveData<AddServiceResponse>()

    fun addServiceSP(addServiceData: AddServiceData) {
        viewModelScope.launch {
            addServiceResponse.postValue(
                sPRepository.addServiceSP(addServiceData)
            )
        }
    }

    fun resetAddServiceSPResponse() {
        addServiceResponse = MutableLiveData<AddServiceResponse>()
    }

    fun getAddServiceSPResponse(): LiveData<AddServiceResponse> {
        return addServiceResponse
    }

    //Get Testimonies

    private var getTestimoniesResponse = MutableLiveData<GetTestimoniesResponse>()

    fun getSPTestimonies(mobileNo: String) {
        viewModelScope.launch {
            getTestimoniesResponse.postValue(
                sPRepository.getSPTestimonies(mobileNo)
            )
        }
    }

    fun resetgetSPTestimoniesPResponse() {
        getTestimoniesResponse = MutableLiveData<GetTestimoniesResponse>()
    }

    fun getSPTestimoniesResponse(): LiveData<GetTestimoniesResponse> {
        return getTestimoniesResponse
    }
}