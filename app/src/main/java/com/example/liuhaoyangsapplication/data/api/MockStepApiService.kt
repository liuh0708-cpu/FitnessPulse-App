package com.example.liuhaoyangsapplication.data.api

import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class MockStepApiService : StepApiService {

    override suspend fun getTodaySteps(): StepResponse {
        delay(800)
        val randomSteps = Random.nextInt(3000, 12000)  // ✅ 去掉 .Default

        return StepResponse(
            steps = randomSteps,
            date = getTodayDate()
        )
    }

    override suspend fun getHistory(days: Int): List<StepResponse> {
        delay(1000)

        return List(days) { index ->
            StepResponse(
                steps = Random.nextInt(2000, 15000),
                date = getPastDate(index)
            )
        }
    }

    private fun getTodayDate(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return format.format(Date())
    }

    private fun getPastDate(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return format.format(calendar.time)
    }
}