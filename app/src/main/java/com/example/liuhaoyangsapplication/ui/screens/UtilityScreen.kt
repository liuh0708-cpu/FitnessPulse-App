package com.example.liuhaoyangsapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.example.liuhaoyangsapplication.ui.components.ProgressCard
import com.example.liuhaoyangsapplication.ui.components.QuickLogButton
import com.example.liuhaoyangsapplication.ui.components.StepDisplay
import com.example.liuhaoyangsapplication.viewmodel.FitnessViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UtilityScreen(
    modifier: Modifier = Modifier,
    viewModel: FitnessViewModel = koinViewModel()
) {
    val fitnessData by viewModel.fitnessData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val dailyGoals by viewModel.dailyGoals.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    var showSuccessMessage by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Today's Progress",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        StepDisplay(
            steps = fitnessData.steps,
            goal = dailyGoals,
            modifier = Modifier
        )

        ProgressCard(
            title = "Steps",
            current = fitnessData.steps,
            goal = dailyGoals,
            unit = "steps",
            icon = Icons.Default.Home
        )

        ProgressCard(
            title = "Active Minutes",
            current = fitnessData.activeMinutes,
            goal = viewModel.activeMinutesGoal.collectAsState().value,
            unit = "min",
            icon = Icons.Default.Settings
        )

        ProgressCard(
            title = "Calories",
            current = fitnessData.caloriesBurned.toInt(),
            goal = viewModel.calorieGoal.collectAsState().value,
            unit = "kcal",
            icon = Icons.Default.Settings
        )

        Spacer(modifier = Modifier.weight(1f))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Text(
                text = viewModel.getMotivationalMessage(),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }

        QuickLogButton(
            onClick = {
                viewModel.logQuickWorkout()
                showSuccessMessage = true

                coroutineScope.launch {
                    delay(3000)
                    showSuccessMessage = false
                }
            }
        )

        if (showSuccessMessage) {
            Text(
                text = "✓ Recorded!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
        }

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}