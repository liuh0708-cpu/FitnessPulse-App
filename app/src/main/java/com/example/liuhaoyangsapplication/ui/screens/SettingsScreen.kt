package com.example.liuhaoyangsapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.example.liuhaoyangsapplication.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val dailyGoals by viewModel.dailyGoals.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Daily Goals",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Step count target: ${dailyGoals.stepGoal} Steps",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Slider(
                        value = dailyGoals.stepGoal.toFloat(),
                        onValueChange = { viewModel.updateStepGoal(it.toInt()) },
                        valueRange = 2000f..15000f,
                        steps = 13,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Exercise time: ${dailyGoals.activeMinutesGoal} minute",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Slider(
                        value = dailyGoals.activeMinutesGoal.toFloat(),
                        onValueChange = { viewModel.updateActiveMinutesGoal(it.toInt()) },
                        valueRange = 10f..120f,
                        steps = 11,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        item {
            Text(
                text = "Personal information",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Weight: ${userProfile.weightKg} kg",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Slider(
                        value = userProfile.weightKg.toFloat(),
                        onValueChange = { viewModel.updateWeight(it.toDouble()) },
                        valueRange = 30f..150f,
                        steps = 24,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}