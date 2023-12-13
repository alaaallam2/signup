package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Domain.SendOtpUseCase
import com.example.myapplication.Domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sendOtpUseCase: SendOtpUseCase) : ViewModel() {

    private val _otpLiveData = MutableLiveData<String>()
    val otpLiveData: LiveData<String>
        get() = _otpLiveData

    var phoneNumber = MutableLiveData<String>()

    fun onSendOtpButtonClicked() {
        val user = User(phoneNumber.value.orEmpty())

        sendOtpUseCase.execute(user, object : SendOtpUseCase.Callback {
            override fun onOtpSent(otp: String) {
                _otpLiveData.postValue(otp)
            }

            override fun onError(message: String) {

            }
        })
    }
}