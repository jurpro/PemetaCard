package com.pemeta.pemetacard.ui.screen.experience.component.tabrow

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.pengalaman.data.DataEnumTabRowExperienceType
import com.pemeta.pemetacard.model.pengalaman.data.DataJobFinalType
import com.pemeta.pemetacard.model.pengalaman.data.DataJobType
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.list.ListViewModel
import com.pemeta.pemetacard.ui.screen.experience.list.component.ListExperienceHome
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.ErrorMessage
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.ExperienceTabContent
import com.pemeta.pemetacard.ui.screen.experience.list.component.helper.LoadingIndicator
import com.pemeta.pemetacard.ui.screen.experience.ongoing.component.ListExperienceOnGoing
import com.pemeta.pemetacard.ui.screen.experience.ongoing.component.OnGoingViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import kotlinx.coroutines.launch

@Composable
fun ExperienceTabRow(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    onGoingViewModel: OnGoingViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
){
    //Perbaikan 2026
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { DataEnumTabRowExperienceType.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    // SOLUSI INFINITE LOOP: Panggil API di sini berdasarkan halaman aktif saat ini
    LaunchedEffect(pagerState.currentPage) {
        when(pagerState.currentPage) {
            0 -> listViewModel.getListExperiences()
            1 -> listViewModel.getListPlanningExperience(typeGlobalId = 1)
            2 -> listViewModel.getListSupervisionExperience(typeGlobalId = 2)
            3 -> onGoingViewModel.getListOnGoingProject()
        }
    }

    Column(
        modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_experiences_more),
            )

            Text(
                text = stringResource(id = R.string.list_experiences),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        }

        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            DataEnumTabRowExperienceType.entries.forEachIndexed { index, currentTab ->
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
                        text = currentTab.textTabExperience.typeWork,
                        fontSize = 11.sp,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = modifier.padding(),
                    ) },
                )
            }
        }

        // SOLUSI CONSTRAINT INFINITY: Gunakan .weight(1f) agar Pager mengisi sisa ruang yang valid
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { page ->
            // Gunakan variabel `page` dari scope Pager, bukan selectedTabIndex.value langsung
            when (page) {
                0 -> {
                    val uiState by listViewModel.uiStateExperience.collectAsState(initial = UiState.Loading)
                    ExperienceTabContent(uiState, paddingValues, navController)
                }
                1 -> {
                    val uiState by listViewModel.uiStatePlanningExperience.collectAsState(initial = UiState.Loading)
                    ExperienceTabContent(uiState, paddingValues, navController)
                }
                2 -> {
                    val uiState by listViewModel.uiStateSupervisionExperience.collectAsState(initial = UiState.Loading)
                    ExperienceTabContent(uiState, paddingValues, navController)
                }
                3 -> {
                    val uiState by onGoingViewModel.uiStateOnGoing.collectAsState(initial = UiState.Loading)
                    when (val state = uiState) {
                        is UiState.Loading -> LoadingIndicator()
                        is UiState.Success -> ListExperienceOnGoing(
                            modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = paddingValues.calculateBottomPadding()),
                            navController = navController,
                            experiences = state.data
                        )
                        is UiState.Error -> ErrorMessage()
                    }
                }
            }
        }
    }



    //Diperbaiki
  /* // private val listViewModelTest: ListViewModel by viewModels()

    Column(
        modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_experiences_more),
            )

            Text(
                text = stringResource(id = R.string.list_experiences),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        }

        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = { DataEnumTabRowExperienceType.entries.size })
        val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            DataEnumTabRowExperienceType.entries.forEachIndexed { index, currentTab ->
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
                        text = currentTab.textTabExperience.typeWork,
                        fontSize = 11.sp,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = modifier.padding(),
                    ) },
                    /* icon = {
                         Icon(
                             imageVector = if (selectedTabIndex.value == index)
                                 currentTab.selectedIcon else currentTab.unselectedIcon,
                             contentDescription = "Tab Icon"
                         )
                     }, */
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
                contentAlignment = Alignment.TopCenter
            ) {
                // Text(text = DataEnumTabRowExperience.entries[selectedTabIndex.value].text)
                when (selectedTabIndex.value) {
                    0 -> listViewModel.uiStateExperience.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { listViewModel.getListExperiences() }
                            is UiState.Success -> {
                                ListExperienceHome(
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        end = 8.dp,
                                        bottom = paddingValues.calculateBottomPadding()
                                    ),
                                    navController = navController,
                                    experiences = uiState.data
                                )
                            }
                            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                        }
                    }

                    1 ->
                        listViewModel.uiStatePlanningExperience.collectAsState(initial = UiState.Loading).value.let { uiState ->
                            when (uiState) {
                                is UiState.Loading -> { listViewModel.getListPlanningExperience(typeGlobalId = 1) }
                                is UiState.Success -> {
                                    ListExperienceHome(
                                        modifier = Modifier.padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = paddingValues.calculateBottomPadding()
                                        ),
                                        navController = navController,
                                        experiences = uiState.data
                                    )
                                }
                                is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                            }
                        }

                    2 -> listViewModel.uiStateSupervisionExperience.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { listViewModel.getListSupervisionExperience(typeGlobalId = 2) }
                            is UiState.Success -> {
                                ListExperienceHome(
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        end = 8.dp,
                                        bottom = paddingValues.calculateBottomPadding()
                                    ),
                                    navController = navController,
                                    experiences = uiState.data
                                )
                            }
                            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                        }
                    }

                    3 -> onGoingViewModel.uiStateOnGoing.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { onGoingViewModel.getListOnGoingProject() }
                            is UiState.Success -> {
                                ListExperienceOnGoing(
                                    modifier = Modifier.padding(
                                        start = 4.dp,
                                        end = 4.dp,
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


                /* when (selectedTabIndex.value) {
                     0 -> ListExperienceAll(
                         id = clientDetailData.id,
                         navController = navController,
                         listExperienceByClientViewModel = listExperienceByClientViewModel)
                     1 -> ListExperienceFinished(
                         id = clientDetailData.id,
                         navController = navController,
                         listExperienceByClientViewModel = listExperienceByClientViewModel)
                     2 -> ListExperienceOnGoing(
                         id = clientDetailData.id,
                         navController = navController,
                         listExperienceByClientViewModel = listExperienceByClientViewModel)
                 } */
            }

            /* TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            tabs = tabs,
        ) */
        }
    } */
}

@Preview(showBackground = true)
@Composable
fun ExperienceTabRowPreview(){
    PemetaCardTheme {
        val dataTitlesType = DataJobType.JobTypeList
        val tabs: @Composable () -> Unit

        ExperienceTabRow(
            paddingValues = PaddingValues(),
            navController = rememberNavController(),
        )
    }
}