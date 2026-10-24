package com.pemeta.pemetacard.ui.screen.team

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.team.Team
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.list.component.ListExperienceHome
import com.pemeta.pemetacard.ui.screen.experience.list.component.TopBar
import com.pemeta.pemetacard.ui.screen.team.component.TeamInfoCard
import com.pemeta.pemetacard.ui.screen.team.component.TeamViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun TeamScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
    teamViewModel: TeamViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
){
    LazyColumn (
        modifier
            .fillMaxWidth()
           // .consumeWindowInsets(paddingValues)
           // .padding(paddingValues)
    ) {
        item {
            TopBar(navController = navController)

            Spacer(modifier = Modifier.height(4.dp))
        }
        item {
            Text(
                text = stringResource(R.string.team_header),
                modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
               // color = WarmGold
            )
        }

        item {
            Text(
                text = stringResource(R.string.team_direksi),
                modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )

            LazyRow(
                modifier.fillMaxWidth()
            ) {
                item {
                    teamViewModel.uiStateTeamManagement.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { teamViewModel.getListTeamManagement() }
                            is UiState.Success -> {
                                uiState.data.forEach { employee ->
                                    TeamInfoCard(
                                        employee = employee,
                                        navController = navController
                                    )
                                }
                            }
                            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.team_design),
                modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                // fontSize = 14.sp,
            )

            LazyRow(
                modifier.fillMaxWidth()
            ) {

                item {
                    teamViewModel.uiStateTeamDesign.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { teamViewModel.getListDesign() }
                            is UiState.Success -> {
                                uiState.data.forEach { employee ->
                                    TeamInfoCard(
                                        employee = employee,
                                        navController = navController
                                    )
                                }
                            }
                            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.team_supervision),
                modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )

            LazyRow(
                modifier.fillMaxWidth()
            ) {
                item {
                    teamViewModel.uiStateTeamSupervision.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { teamViewModel.getListSupervision() }
                            is UiState.Success -> {
                                uiState.data.forEach { employee ->
                                    TeamInfoCard(
                                        employee = employee,
                                        navController = navController
                                    )
                                }
                            }
                            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier.padding(top = 12.dp))
            CopyrightPemeta()
            Spacer(modifier
                .padding(
                    top = 12.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun TeamScreenPreview(){
    PemetaCardTheme {
        TeamScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}