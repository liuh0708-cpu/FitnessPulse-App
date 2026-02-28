package com.example.liuhaoyangsapplication.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface StepApiService {

    @GET("steps/today")
    suspend fun getTodaySteps(): StepResponse

    @GET("steps/history")
    suspend fun getHistory(@Query("days") days: Int): List<StepResponse>
}

data class StepResponse(
    val steps: Int,
    val date: String,
    val message: String = "success"
)