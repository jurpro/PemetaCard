package com.pemeta.pemetacard.ui.screen.client.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.pemeta.pemetacard.ui.screen.client.list.component.ListClient
import com.pemeta.pemetacard.ui.screen.client.list.component.ListClientViewModel
import com.pemeta.pemetacard.ui.screen.experience.list.component.TopBar
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.ErrorMessage
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.LoadingIndicator
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ClientListScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
    listClientViewModel: ListClientViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
    LaunchedEffect(Unit) {
        listClientViewModel.getListClients()
    }

    val uiState by listClientViewModel.uiStateClient.collectAsState(initial = UiState.Loading)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
           // .consumeWindowInsets(paddingValues)
           // .background(MaterialTheme.colorScheme.primaryContainer)
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
            when (val state = uiState) {
                is UiState.Loading -> {
                    LoadingIndicator()
                }
                is UiState.Success -> {
                    ListClient(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                        navController = navController,
                        clients = state.data
                    )
                }
                is UiState.Error -> {
                    ErrorMessage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClientListScreenPreview(){
    PemetaCardTheme {
        ClientListScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}