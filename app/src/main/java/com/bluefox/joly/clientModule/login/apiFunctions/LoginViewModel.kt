package com.bluefox.joly.clientModule.login.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluefox.joly.clientModule.login.modelClass.LoginData
import com.bluefox.joly.clientModule.login.modelClass.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private var currentFragment = MutableLiveData<Int>()

    fun setCurrentFragment(currFragment: Int)
    {
        currentFragment.value=currFragment
    }

    fun getFragment(): LiveData<Int>
    {
        return currentFragment
    }


    private var loginResponse = MutableLiveData<LoginResponse>()

    fun validateLogin(loginData: LoginData) {
        viewModelScope.launch {
            loginResponse.postValue(
                loginRepository.validateLogin(loginData)
            )
        }
    }

    fun resetLoginResponse() {
        loginResponse = MutableLiveData<LoginResponse>()
    }

    fun getLoginResponse(): LiveData<LoginResponse> {
        return loginResponse
    }

}