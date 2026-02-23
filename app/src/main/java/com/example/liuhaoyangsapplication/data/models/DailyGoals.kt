package com.example.liuhaoyangsapplication.data.models

/**
 * Daily Goal Setting
 * @param stepGoal
 * @param activeMinutesGoal
 * @param calorieGoal
 */
data class DailyGoals(
    val stepGoal: Int = 10000,
    val activeMinutesGoal: Int = 30,
    val calorieGoal: Double = 500.0
)