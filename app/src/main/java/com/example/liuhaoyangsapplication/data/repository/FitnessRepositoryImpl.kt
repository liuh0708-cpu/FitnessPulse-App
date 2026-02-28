package com.example.liuhaoyangsapplication.data.repository

import com.example.liuhaoyangsapplication.data.models.DailyGoals
import com.example.liuhaoyangsapplication.data.models.FitnessData
import com.example.liuhaoyangsapplication.data.models.UserProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FitnessRepositoryImpl : FitnessRepository {

    private val _dailyGoals = MutableStateFlow(DailyGoals())
    private val _userProfile = MutableStateFlow(UserProfile())

    private var todayFitnessData = FitnessData(
        steps = 0,
        activeMinutes = 0,
        caloriesBurned = 0.0
    )

    override suspend fun getTodayFitnessData(): FitnessData {
        delay(500)
        return todayFitnessData
    }

    override suspend fun logWorkout(minutes: Int): FitnessData {
        delay(800)

        val profile = _userProfile.value

        val additionalSteps = minutes * 100

        val caloriesPerMinute = profile.weightKg * 0.05
        val additionalCalories = minutes * caloriesPerMinute

        todayFitnessData = todayFitnessData.copy(
            steps = todayFitnessData.steps + additionalSteps,
            activeMinutes = todayFitnessData.activeMinutes + minutes,
            caloriesBurned = todayFitnessData.caloriesBurned + additionalCalories
        )

        return todayFitnessData
    }

    override suspend fun updateUserProfile(profile: UserProfile) {
        _userProfile.value = profile
    }

    override fun getDailyGoals(): Flow<DailyGoals> {
        return _dailyGoals.asStateFlow()
    }

    override suspend fun saveDailyGoals(goals: DailyGoals) {
        _dailyGoals.value = goals
    }

    override suspend fun getUserProfile(): UserProfile {
        return _userProfile.value
    }
}