package com.example.myapplication.Data

import com.example.myapplication.Domain.SendOtpUseCase
import com.example.myapplication.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://eihospital.com/eihospital/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }

    @Provides
    fun provideSendOtpUseCase(userRepository: UserRepository): SendOtpUseCase {
        return SendOtpUseCase(userRepository)
    }

    @Provides
    fun provideMainViewModel(sendOtpUseCase: SendOtpUseCase): MainViewModel {
        return MainViewModel(sendOtpUseCase)
    }
}