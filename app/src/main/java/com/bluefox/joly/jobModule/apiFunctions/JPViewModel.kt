package com.bluefox.joly.jobModule.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluefox.joly.jobModule.jobProviderModule.modalClass.GetApplicationResponse
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

    private var getUpdateJobResponse = MutableLiveData<PostJobResponse>()

    fun updateJob(postJobData: PostJobData) {
        viewModelScope.launch {
            getUpdateJobResponse.postValue(
                jpRepository.getUpdateResponse(postJobData)
            )
        }
    }

    fun resetUpdateJobResponse() {
        getUpdateJobResponse = MutableLiveData<PostJobResponse>()
    }

    fun getUpdateJobResponse(): LiveData<PostJobResponse> {
        return getUpdateJobResponse
    }


    private var getUpdateJobStatusResponse = MutableLiveData<PostJobResponse>()

    fun updateJobStatus(jobId: String, jobStatus: String) {
        viewModelScope.launch {
            getUpdateJobStatusResponse.postValue(
                jpRepository.getUpdatedJobStatusResponse(jobId,jobStatus)
            )
        }
    }

    fun resetUpdateJobStatusResponse() {
        getUpdateJobStatusResponse = MutableLiveData<PostJobResponse>()
    }

    fun getUpdateJobStatusResponse(): LiveData<PostJobResponse> {
        return getUpdateJobStatusResponse
    }


    private var getUpdateJobApplicationsResponse = MutableLiveData<GetApplicationResponse>()

    fun updateJobApplications(jobId: String) {
        viewModelScope.launch {
            getUpdateJobApplicationsResponse.postValue(
                jpRepository.getPostedApplicationsResponse(jobId)
            )
        }
    }

    fun resetUpdateJobApplicationsResponse() {
        getUpdateJobApplicationsResponse = MutableLiveData<GetApplicationResponse>()
    }

    fun getUpdateJobApplicationsResponse(): LiveData<GetApplicationResponse> {
        return getUpdateJobApplicationsResponse
    }

    private var getPostedJobsResponseJS = MutableLiveData<GetPostedJobsResponse>()

    fun getAllJobs(userId: String) {
        viewModelScope.launch {
            getPostedJobsResponseJS.postValue(
                jpRepository.getAllJobs(userId)
            )
        }
    }

    fun resetGetAllJobsResponse() {
        getPostedJobsResponseJS = MutableLiveData<GetPostedJobsResponse>()
    }

    fun getAllJobsResponse(): LiveData<GetPostedJobsResponse> {
        return getPostedJobsResponseJS
    }

}