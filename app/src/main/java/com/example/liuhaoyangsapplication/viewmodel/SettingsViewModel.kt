package com.example.liuhaoyangsapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liuhaoyangsapplication.data.models.DailyGoals
import com.example.liuhaoyangsapplication.data.models.UserProfile
import com.example.liuhaoyangsapplication.data.repository.FitnessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: FitnessRepository
) : ViewModel() {

    private val _dailyGoals = MutableStateFlow(DailyGoals())
    val dailyGoals: StateFlow<DailyGoals> = _dailyGoals.asStateFlow()

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadSettings()
    }


    private fun loadSettings() {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                repository.getDailyGoals().collect { goals ->
                    _dailyGoals.value = goals
                }

                val profile = repository.getUserProfile()
                _userProfile.value = profile
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun updateStepGoal(goal: Int) {
        viewModelScope.launch {
            val updated = _dailyGoals.value.copy(stepGoal = goal)
            repository.saveDailyGoals(updated)
        }
    }


    fun updateActiveMinutesGoal(goal: Int) {
        viewModelScope.launch {
            val updated = _dailyGoals.value.copy(activeMinutesGoal = goal)
            repository.saveDailyGoals(updated)
        }
    }


    fun updateWeight(weight: Double) {
        viewModelScope.launch {
            val updated = _userProfile.value.copy(weightKg = weight)
            repository.updateUserProfile(updated)
            _userProfile.value = updated
        }
    }


    fun updateHeight(height: Double) {
        viewModelScope.launch {
            val updated = _userProfile.value.copy(heightCm = height)
            repository.updateUserProfile(updated)
            _userProfile.value = updated
        }
    }


    fun updateAge(age: Int) {
        viewModelScope.launch {
            val updated = _userProfile.value.copy(age = age)
            repository.updateUserProfile(updated)
            _userProfile.value = updated
        }
    }

    fun toggleUnit() {
        viewModelScope.launch {
            val updated = _userProfile.value.copy(useMetric = !_userProfile.value.useMetric)
            repository.updateUserProfile(updated)
            _userProfile.value = updated
        }
    }
}