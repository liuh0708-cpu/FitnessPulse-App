package com.example.liuhaoyangsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liuhaoyangsapplication.data.models.FitnessData
import com.example.liuhaoyangsapplication.data.repository.FitnessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FitnessViewModel(
    private val repository: FitnessRepository
) : ViewModel() {

    private val _fitnessData = MutableStateFlow(FitnessData())
    val fitnessData: StateFlow<FitnessData> = _fitnessData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _dailyGoals = MutableStateFlow(10000)
    val dailyGoals: StateFlow<Int> = _dailyGoals.asStateFlow()

    private val _activeMinutesGoal = MutableStateFlow(30)
    val activeMinutesGoal: StateFlow<Int> = _activeMinutesGoal.asStateFlow()

    private val _calorieGoal = MutableStateFlow(500)
    val calorieGoal: StateFlow<Int> = _calorieGoal.asStateFlow()

    init {
        loadTodayData()
        loadDailyGoals()
    }

    private fun loadDailyGoals() {
        viewModelScope.launch {
            repository.getDailyGoals().collect { goals ->
                _dailyGoals.value = goals.stepGoal
                _activeMinutesGoal.value = goals.activeMinutesGoal
                _calorieGoal.value = goals.calorieGoal.toInt()
            }
        }
    }

    fun loadTodayData() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val data = repository.getTodayFitnessData()
                _fitnessData.value = data
            } catch (e: Exception) {
                _errorMessage.value = "Loading failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logQuickWorkout(minutes: Int = 10) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val updatedData = repository.logWorkout(minutes)
                _fitnessData.value = updatedData
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Log failure: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMotivationalMessage(): String {
        val steps = _fitnessData.value.steps
        val goal = _dailyGoals.value

        return when {
            steps == 0 -> "Go GO GO!"
            steps < goal -> "Go GO GO!"
            else -> "Well done!"
        }
    }
}