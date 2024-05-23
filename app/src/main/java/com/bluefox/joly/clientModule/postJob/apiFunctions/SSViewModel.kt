package com.bluefox.joly.clientModule.postJob.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluefox.joly.clientModule.postJob.modalClass.GetCategoriesResponse
import com.bluefox.joly.clientModule.postJob.modalClass.GetJobsResponse
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkData
import com.bluefox.joly.clientModule.postJob.modalClass.PostWorkResponse
import com.bluefox.joly.clientModule.viewJob.modalClass.GetWorkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SSViewModel @Inject constructor(
    private val ssRepository: SSRepository
) : ViewModel() {

    private var getCategoriesResponse = MutableLiveData<GetCategoriesResponse>()

    fun getCategories() {
        viewModelScope.launch {
            getCategoriesResponse.postValue(
                ssRepository.getCategories()
            )
        }
    }

    fun resetCategories() {
        getCategoriesResponse = MutableLiveData<GetCategoriesResponse>()
    }

    fun getCategoriesResponse(): LiveData<GetCategoriesResponse> {
        return getCategoriesResponse
    }

    private var getJobsResponse = MutableLiveData<GetJobsResponse>()

    fun getJobs() {
        viewModelScope.launch {
            getJobsResponse.postValue(
                ssRepository.getJobs()
            )
        }
    }

    fun resetJobsResponse() {
        getJobsResponse = MutableLiveData<GetJobsResponse>()
    }

    fun getJobsResponse(): LiveData<GetJobsResponse> {
        return getJobsResponse
    }

    private var postWorkResponse = MutableLiveData<PostWorkResponse>()

    fun postWork(postWorkData: PostWorkData) {
        viewModelScope.launch {
            postWorkResponse.postValue(
                ssRepository.postWork(postWorkData)
            )
        }
    }

    fun resetPostWorkResponse() {
        postWorkResponse = MutableLiveData<PostWorkResponse>()
    }

    fun getPostWorkResponse(): LiveData<PostWorkResponse> {
        return postWorkResponse
    }

    private var getWorkResponse = MutableLiveData<GetWorkResponse>()

    fun getSSWork(mobileNo: String) {
        viewModelScope.launch {
            getWorkResponse.postValue(
                ssRepository.getSSWorks(mobileNo)
            )
        }
    }

    fun resetGetSSWorkResponse() {
        getWorkResponse = MutableLiveData<GetWorkResponse>()
    }

    fun getSSWorkResponse(): LiveData<GetWorkResponse> {
        return getWorkResponse
    }
}