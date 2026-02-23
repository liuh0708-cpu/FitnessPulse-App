package com.example.liuhaoyangsapplication.data.models

/**
 * Fitness data
 * @param steps
 * @param activeMinutes
 * @param caloriesBurned
 * @param lastUpdated
 */
data class FitnessData(
    val steps: Int = 0,
    val activeMinutes: Int = 0,
    val caloriesBurned: Double = 0.0,
    val lastUpdated: Long = System.currentTimeMillis()
) {
    /**
     * Calculate progress percentage
     */
    fun getStepProgress(goal: Int): Float {
        return if (goal > 0) (steps.toFloat() / goal).coerceIn(0f, 1f) else 0f
    }
}