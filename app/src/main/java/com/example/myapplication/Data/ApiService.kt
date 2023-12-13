package com.example.myapplication.Data

import com.example.myapplication.Domain.OtpResponse
import com.example.myapplication.Domain.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("sendOtp")
    fun sendOtp(@Body user: User): Call<OtpResponse>
}