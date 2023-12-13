package com.example.myapplication.Data

import android.util.Log
import com.example.myapplication.Domain.OtpResponse
import com.example.myapplication.Domain.User
import retrofit2.Call
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {

    interface Callback {
        fun onOtpSent(otp: String)
        fun onError(message: String)
    }

    fun sendOtp(user: User, callback: Callback) {
        apiService.sendOtp(user).enqueue(object : retrofit2.Callback<OtpResponse> {
            override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
                if (response.isSuccessful) {
                    val otp = response.body()?.otp.orEmpty()
                    callback.onOtpSent(otp)
                } else {
                    callback.onError("Failed to send OTP")
                }
            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                callback.onError("Network error")
            }
        })
    }
}