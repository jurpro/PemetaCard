package com.pemeta.pemetacard.ui.screen.legal.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.lagilitas.data.DataLegalitas
import com.pemeta.pemetacard.model.lagilitas.model.Legalitas
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun LegalitasMenuGrid(
    modifier: Modifier = Modifier,
    listLegalitas: List<Legalitas>,
    navController: NavController,
   // paddingValues: PaddingValues,
    listLegalitasViewModel: ListLegalitasViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
){
    val uiState by listLegalitasViewModel.uiStateLegal.collectAsState(initial = UiState.Loading)

    LaunchedEffect(Unit) {
        listLegalitasViewModel.getListLegalitas()
    }

   // val uiState by listLegalitasViewModel.uiStateLegal.collectAsState(initial = UiState.Loading)

    when(val state = uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                   // .weight(1f) // Fix unbounded height inside Column
                    .padding(horizontal = 16.dp)
            ) {
                items(state.data.size) { index ->
                    ItemLegalitasCard(
                        navController = navController,
                        legalitas = state.data[index]
                    )
                }
            }
        }

        is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
    }

   /* Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_legalitas_more),
            )

            Text(
                text = stringResource(id = R.string.list_legalitas),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        }

        val uiState by listLegalitasViewModel.uiStateLegal.collectAsState(initial = UiState.Loading)

        when(val state = uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Fix unbounded height inside Column
                        .padding(horizontal = 16.dp)
                ) {
                    items(state.data.size) { index ->
                        ItemLegalitasCard(
                            navController = navController,
                            legalitas = state.data[index]
                        )
                    }
                }
            }

            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
        }




       /* LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            // contentPadding = paddingValues,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp)
        ){
            items(listLegalitas.size) { item ->
                listLegalitasViewModel.uiStateLegal.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> { listLegalitasViewModel.getListLegalitas() }
                        is UiState.Success -> {
                            ItemLegalitasCard(
                                navController = navController,
                                legalitas = uiState.data[item]
                            )
                        }
                        is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                    }
                }
            }
        } */
    } */
}

@Preview(showBackground = true)
@Composable
fun LegalitasMenuGridPreview(){
    PemetaCardTheme {

        val dataListLegalitas = DataLegalitas.LegalitasList

        LegalitasMenuGrid(
            listLegalitas = dataListLegalitas,
            navController = rememberNavController(),
        )
    }
}