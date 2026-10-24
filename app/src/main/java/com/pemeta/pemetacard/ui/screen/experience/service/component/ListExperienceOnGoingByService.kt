package com.pemeta.pemetacard.ui.screen.experience.service.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.utils.NoOnGoingJob
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.pengalaman.data.ServicesData.listServicesData
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.service.ListServiceViewModel
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.TypeListExperienceOnGoing
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListExperienceOnGoingByService(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues,
    id: Int,
    listExperienceByServiceViewModel: ListServiceViewModel = viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
){
    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(4.dp))

        val serviceBudgetDetailData = listServicesData[id-1]

        listExperienceByServiceViewModel.uiStateOnGoingExperienceByService.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> { listExperienceByServiceViewModel.getListOnGoingByBudget(serviceId = serviceBudgetDetailData.id) }
                is UiState.Success -> {
                    if (uiState.data.isEmpty()) {
                        NoOnGoingJob()
                    } else {
                        TypeListExperienceOnGoing(
                            modifier = Modifier.padding(
                                start = 8.dp,
                                end = 8.dp,
                                bottom = paddingValues.calculateBottomPadding()
                            ),
                            navController = navController,
                            experiencesTypeOnGoing = uiState.data
                        )
                    }
                }
                is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ListExperienceOnGoingByServicePreview(){
    PemetaCardTheme {
        val serviceDetailDataPreview = listServicesData[2]
        ListExperienceOnGoingByService(
            id = serviceDetailDataPreview.id,
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}