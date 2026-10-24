package com.pemeta.pemetacard.ui.screen.experience.component.tabrow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.model.pengalaman.data.DataEnumTabRowExperience
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.client.list.component.ExperienceByClientViewModel
import com.pemeta.pemetacard.ui.screen.client.list.component.ListExperienceAll
import com.pemeta.pemetacard.ui.screen.client.list.component.ListExperienceFinished
import com.pemeta.pemetacard.ui.screen.client.list.component.ListExperienceOnGoing
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import kotlinx.coroutines.launch

@Composable
fun ExperienceTabRowByClient(
    paddingValues: PaddingValues,
    navController: NavController,
    id: Int,
    modifier: Modifier = Modifier,
    listExperienceByClientViewModel: ExperienceByClientViewModel = viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
){
    //Perbaikan 2026
    val clientDetailData = DataPemetaClient.ClientList[id-1]

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { DataEnumTabRowExperience.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    LaunchedEffect(pagerState.currentPage, clientDetailData.id){
        when(pagerState.currentPage){
            0 -> { listExperienceByClientViewModel.getListExperienceByClient(clientId = clientDetailData.id) }
            1 -> { listExperienceByClientViewModel.getListFinishedByClient(clientId = clientDetailData.id) }
            2 -> { listExperienceByClientViewModel.getListOnGoingByClient(clientId = clientDetailData.id) }
        }
    }

    // SOLUSI CRASH: Bungkus layout menggunakan Column induk dengan batas tinggi yang jelas
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SecondaryTabRow (
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {
            DataEnumTabRowExperience.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
                    text = { Text(
                        text = currentTab.textTabExperience.name,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(2.dp),
                    ) },
                    /* icon = {
                         Icon(
                             imageVector = if (selectedTabIndex.value == index)
                                 currentTab.selectedIcon else currentTab.unselectedIcon,
                             contentDescription = "Tab Icon"
                         )
                     } */
                )
            }
        }

        // SOLUSI CRASH: Gunakan .weight(1f) agar tinggi item scrollable di dalamnya terhitung dengan valid
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                // Text(text = DataEnumTabRowExperience.entries[selectedTabIndex.value].text)
                when (page) {
                    0 -> ListExperienceAll(
                        id = clientDetailData.id,
                        navController = navController,
                        paddingValues = paddingValues,
                        listExperienceByClientViewModel = listExperienceByClientViewModel)
                    1 -> ListExperienceFinished(
                        id = clientDetailData.id,
                        navController = navController,
                        paddingValues = paddingValues,
                        listExperienceByClientViewModel = listExperienceByClientViewModel)
                    2 -> ListExperienceOnGoing(
                        id = clientDetailData.id,
                        navController = navController,
                        paddingValues = paddingValues,
                        listExperienceByClientViewModel = listExperienceByClientViewModel)
                }
            }
        }
    }

   /* SecondaryTabRow(
        selectedTabIndex = selectedTabIndex.value,
        modifier = Modifier.fillMaxWidth()
    ) {
        DataEnumTabRowExperience.entries.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex.value == index,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.outline,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(currentTab.ordinal)
                    }
                },
                text = { Text(
                    text = currentTab.textTabExperience.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(2.dp),
                ) },
                /* icon = {
                     Icon(
                         imageVector = if (selectedTabIndex.value == index)
                             currentTab.selectedIcon else currentTab.unselectedIcon,
                         contentDescription = "Tab Icon"
                     )
                 } */
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Text(text = DataEnumTabRowExperience.entries[selectedTabIndex.value].text)
            when (selectedTabIndex.value) {
                0 -> ListExperienceAll(
                    id = clientDetailData.id,
                    navController = navController,
                    paddingValues = paddingValues,
                    listExperienceByClientViewModel = listExperienceByClientViewModel)
                1 -> ListExperienceFinished(
                    id = clientDetailData.id,
                    navController = navController,
                    paddingValues = paddingValues,
                    listExperienceByClientViewModel = listExperienceByClientViewModel)
                2 -> ListExperienceOnGoing(
                    id = clientDetailData.id,
                    navController = navController,
                    paddingValues = paddingValues,
                    listExperienceByClientViewModel = listExperienceByClientViewModel)
            }
        }
    } */
}

@Preview(showBackground = true)
@Composable
fun ExperienceTabRowByClientPreview(){
    PemetaCardTheme {
        val dataExperienceTabRowByClient = DataPemetaClient.ClientList[1]

        ExperienceTabRowByClient(
            paddingValues = PaddingValues(),
            navController = rememberNavController(),
            id = dataExperienceTabRowByClient.id
        )
    }
}