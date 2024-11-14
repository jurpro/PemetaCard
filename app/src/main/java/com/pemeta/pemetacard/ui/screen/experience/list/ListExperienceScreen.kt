package com.pemeta.pemetacard.ui.screen.experience.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.list.component.ListExperienceHome
import com.pemeta.pemetacard.ui.screen.experience.list.component.TopBar
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListExperienceScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ){
        item {
            Column(modifier = modifier.padding(8.dp)) {
                // SearchBar()

                TopBar(navController = navController)

                Spacer(modifier = Modifier.height(4.dp))

               // PromotionHome(navController = navController)
            }
        }
        item {
            listViewModel.uiStateExperience.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> { listViewModel.getListExperiences() }
                    is UiState.Success -> {
                        ListExperienceHome(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = paddingValues.calculateBottomPadding()
                            ),
                            navController = navController,
                            experiences = uiState.data
                        )
                    }
                    is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListExperienceScreenPreview(){
    PemetaCardTheme {
        ListExperienceScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}