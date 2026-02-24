package com.example.liuhaoyangsapplication.data.repository

import com.example.liuhaoyangsapplication.data.models.DailyGoals
import com.example.liuhaoyangsapplication.data.models.FitnessData
import com.example.liuhaoyangsapplication.data.models.UserProfile
import kotlinx.coroutines.flow.Flow


interface FitnessRepository {

    suspend fun getTodayFitnessData(): FitnessData

    suspend fun logWorkout(minutes: Int): FitnessData

    suspend fun updateUserProfile(profile: UserProfile)

    fun getDailyGoals(): Flow<DailyGoals>

    suspend fun saveDailyGoals(goals: DailyGoals)

    suspend fun getUserProfile(): UserProfile
}