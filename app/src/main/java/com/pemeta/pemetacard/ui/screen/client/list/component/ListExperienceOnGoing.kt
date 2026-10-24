package com.pemeta.pemetacard.ui.screen.client.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.client.DataPemetaClient.ClientList
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListExperienceOnGoing(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    id: Int,
    listExperienceByClientViewModel: ExperienceByClientViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    // Perbaikan 2026
    val clientDetailData = ClientList[id-1]

    // Panggil API hanya satu kali saat komponen ini pertama kali dimuat
    LaunchedEffect(clientDetailData.id) {
        listExperienceByClientViewModel.getListOnGoingByClient(clientId = clientDetailData.id)
    }

    Column(
        modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        val uiState by listExperienceByClientViewModel.uiStateOnGoingExperienceByClient.collectAsState(initial = UiState.Loading)
        when (val state = uiState) {
            is UiState.Loading ->{
                // Tampilkan komponen loading di sini (misal: CircularProgressIndicator)
                // Membungkus loading indicator agar posisinya tepat di tengah area yang tersedia
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary // Mengikuti warna tema aplikasi Anda
                    )
                }
            }
            is UiState.Success -> {
                ListExperienceAllClient(
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    navController = navController,
                    experiencesAll = state.data
                )
            }
            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
        }
    }
   /* Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(4.dp))

        val clientDetailData = ClientList[id-1]

        listExperienceByClientViewModel.uiStateOnGoingExperienceByClient.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> { listExperienceByClientViewModel.getListOnGoingByClient(clientId = clientDetailData.id) }
                is UiState.Success -> {
                    ListExperienceOnGoingClient(
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                        navController = navController,
                        experiencesOnGoing = uiState.data
                    )
                }
                is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
            }
        }
    } */
}

@Preview(showBackground = true)
@Composable
fun ListExperienceOnGoingPreview(){
    PemetaCardTheme {

        val listExperienceDataFinishedPreview = DataPemetaExperience.CompanyExperienceList
        val clientDetailData = ClientList[1]

        ListExperienceOnGoing(
            // experiencesFinished = listExperienceDataFinishedPreview,
            id = clientDetailData.id,
            navController = rememberNavController(),
            paddingValues = PaddingValues()
        )
    }
}