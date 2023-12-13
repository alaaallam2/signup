package com.example.myapplication.Domain

import com.example.myapplication.Data.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(private val userRepository: UserRepository) {

    interface Callback {
        fun onOtpSent(otp: String)
        fun onError(message: String)
    }

    fun execute(user: User, callback: Callback) {
        userRepository.sendOtp(user, object : UserRepository.Callback {
            override fun onOtpSent(otp: String) {
                callback.onOtpSent(otp)
            }

            override fun onError(message: String) {
                callback.onError(message)
            }
        })
    }
}