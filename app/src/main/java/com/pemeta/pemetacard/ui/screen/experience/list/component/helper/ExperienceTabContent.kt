package com.pemeta.pemetacard.ui.screen.experience.list.component.helper

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.ui.screen.experience.list.component.ListExperienceHome

@Composable
fun ExperienceTabContent(
    uiState: UiState<List<CompanyExperience>>,
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is UiState.Loading -> LoadingIndicator()
        is UiState.Success -> {
            ListExperienceHome(
                modifier = modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                navController = navController,
                experiences = uiState.data
            )
        }
        is UiState.Error -> ErrorMessage()
    }
}