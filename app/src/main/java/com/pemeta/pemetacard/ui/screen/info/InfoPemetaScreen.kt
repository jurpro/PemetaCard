package com.pemeta.pemetacard.ui.screen.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.newsinfo.data.HighLightData
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.client.list.component.ListClient
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRow
import com.pemeta.pemetacard.ui.screen.experience.list.component.TopBar
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.ErrorMessage
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.LoadingIndicator
import com.pemeta.pemetacard.ui.screen.info.component.ItemNewsCard
import com.pemeta.pemetacard.ui.screen.info.component.ProjectInsightCard
import com.pemeta.pemetacard.ui.screen.info.component.ServiceListInfo
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfo
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfoViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun InfoPemetaScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    navController: NavController,
    listsNewsInfoViewModel: ListNewsInfoViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
){
    // 1. SOLUSI INFINITE LOOP: Panggil API hanya satu kali saat layar pertama kali dibuka
    LaunchedEffect(Unit) {
        listsNewsInfoViewModel.getListHeadlines()
        listsNewsInfoViewModel.getListNewsInfos()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        // Background decorative elements
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-50).dp, y = 100.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.1f))
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .offset(x = 250.dp, y = 300.dp)
                .clip(CircleShape)
                .background(Color.Yellow.copy(alpha = 0.2f))
        )

        // Ambil State data secara asinkron di luar blok render
        val headlineUiState by listsNewsInfoViewModel.uiStateHeadline.collectAsState(initial = UiState.Loading)
        val newsUiState by listsNewsInfoViewModel.uiStateNews.collectAsState(initial = UiState.Loading)

        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
        ){
            item {
                Column(modifier = modifier.padding(8.dp)) {
                    // SearchBar()
                    TopBar(navController = navController)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            item {
                when(val state = headlineUiState) {
                    is UiState.Loading -> {
                        LoadingIndicator()
                    }
                    is UiState.Success -> {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(items = state.data){ project ->
                                ProjectInsightCard(
                                    navController = navController,
                                    headline = project
                                )
                            }
                        }
                    }
                    is UiState.Error -> { ErrorMessage() }
                }
            }

            item {
                Row(
                    modifier = Modifier.padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                        contentDescription = stringResource(id = R.string.menu_info_news),
                    )

                    Text(
                        text = stringResource(id = R.string.menu_info_news),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(2.dp),
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
            }

            //List Experiences filtered by Type of Work = Planning and Supervision
            item {
                when(val state = newsUiState) {
                    is UiState.Loading -> { LoadingIndicator() }
                    is UiState.Success -> {
                        ListNewsInfo(
                            modifier = Modifier.padding(
                                start = 8.dp,
                                end = 8.dp,
                                // bottom = paddingValues.calculateBottomPadding()
                            ),
                            navController = navController,
                            newsInfos = state.data
                        )
                    }
                    is UiState.Error -> { ErrorMessage()}
                }
            }

            item {
                ServiceListInfo(
                    navController = navController
                    // modifier.padding(start = 8.dp, end = 8.dp)
                )
            }

            item {
                Spacer(modifier.padding(top = 12.dp))
                CopyrightPemeta()
                Spacer(modifier.padding(
                    top = 12.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPemetaScreenPreview(){
    PemetaCardTheme {
        InfoPemetaScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController(),
            lazyListState = rememberLazyListState()
        )
    }
}