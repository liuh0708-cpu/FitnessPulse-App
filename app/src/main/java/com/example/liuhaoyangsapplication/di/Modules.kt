package com.example.liuhaoyangsapplication.di

import com.example.liuhaoyangsapplication.data.api.ApiClient
import com.example.liuhaoyangsapplication.data.api.StepApiService
import com.example.liuhaoyangsapplication.data.repository.FitnessRepository
import com.example.liuhaoyangsapplication.data.repository.FitnessRepositoryImpl
import com.example.liuhaoyangsapplication.viewmodel.FitnessViewModel
import com.example.liuhaoyangsapplication.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<StepApiService> { ApiClient.getStepApiService() }

    single<FitnessRepository> { FitnessRepositoryImpl() }

    viewModel { FitnessViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}