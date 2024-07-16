package com.bluefox.joly.jobModule.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetPostedJobsResponse
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobData
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.PostJobResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JPViewModel @Inject constructor(
    private val jpRepository: JPRepository
) : ViewModel() {

    private var postJobResponse = MutableLiveData<PostJobResponse>()

    fun postJob(postJobData: PostJobData) {
        viewModelScope.launch {
            postJobResponse.postValue(
                jpRepository.getPostJobResponse(postJobData)
            )
        }
    }

    fun resetPostJobResponse() {
        postJobResponse = MutableLiveData<PostJobResponse>()
    }

    fun getPostJobResponse(): LiveData<PostJobResponse> {
        return postJobResponse
    }

    private var getPostedJobsResponse = MutableLiveData<GetPostedJobsResponse>()

    fun getPostedJobsJP(userId: String) {
        viewModelScope.launch {
            getPostedJobsResponse.postValue(
                jpRepository.getPostedJobsJP(userId)
            )
        }
    }

    fun resetGetPostedJobsJP() {
        getPostedJobsResponse = MutableLiveData<GetPostedJobsResponse>()
    }

    fun getPostedJobsJPResponse(): LiveData<GetPostedJobsResponse> {
        return getPostedJobsResponse
    }
}