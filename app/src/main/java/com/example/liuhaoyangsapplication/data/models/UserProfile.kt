package com.example.liuhaoyangsapplication.data.models

/**
 * User Information
 * @param weightKg
 * @param heightCm
 * @param age
 * @param gender
 * @param useMetric
 */
data class UserProfile(
    val weightKg: Double = 70.0,
    val heightCm: Double = 170.0,
    val age: Int = 30,
    val gender: String = "other",
    val useMetric: Boolean = true
) {
    /**
     * Calculate BMI index
     */
    fun calculateBMI(): Double {
        val heightInMeters = heightCm / 100
        return weightKg / (heightInMeters * heightInMeters)
    }

    /**
     * Get BMI
     */
    fun getBmiCategory(): String {
        val bmi = calculateBMI()
        return when {
            bmi < 18.5 -> "Thin"
            bmi < 24.9 -> "Normal"
            bmi < 29.9 -> "Slightly overweight"
            else -> "Obesity"
        }
    }
}