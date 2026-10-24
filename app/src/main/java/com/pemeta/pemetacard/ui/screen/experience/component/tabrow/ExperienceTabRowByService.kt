package com.pemeta.pemetacard.ui.screen.experience.component.tabrow

import androidx.compose.foundation.layout.Box
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
import com.pemeta.pemetacard.model.pengalaman.data.DataJobType
import com.pemeta.pemetacard.model.pengalaman.data.ServicesData
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.service.ListServiceViewModel
import com.pemeta.pemetacard.ui.screen.experience.service.component.ListExperienceAllService
import com.pemeta.pemetacard.ui.screen.experience.service.component.ListExperienceFinishedByService
import com.pemeta.pemetacard.ui.screen.experience.service.component.ListExperienceOnGoingByService
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.ListExperienceAllType
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.ListExperienceFinishedByType
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.ListExperienceOnGoingByType
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import kotlinx.coroutines.launch

@Composable
fun ExperienceTabRowByService(
    paddingValues: PaddingValues,
    navController: NavController,
    id: Int,
    modifier: Modifier = Modifier,
    listExperienceByServiceViewModel: ListServiceViewModel = viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
){
    val serviceDetailData = ServicesData.listServicesData[id-1]

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { DataEnumTabRowExperience.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    SecondaryTabRow(
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
                0 -> ListExperienceAllService(
                    id = serviceDetailData.id,
                    navController = navController,
                    paddingValues = paddingValues,
                    listExperienceByServiceViewModel = listExperienceByServiceViewModel)
                1 -> ListExperienceFinishedByService(
                    id = serviceDetailData.id,
                    navController = navController,
                    paddingValues = paddingValues,
                    listExperienceByServiceViewModel = listExperienceByServiceViewModel)
                2 -> ListExperienceOnGoingByService(
                    id = serviceDetailData.id,
                    navController = navController,
                    paddingValues = paddingValues,
                    listExperienceByServiceViewModel = listExperienceByServiceViewModel)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExperienceTabRowByServicePreview(){
    PemetaCardTheme {
        val dataExperienceTabRowByService = ServicesData.listServicesData[3]
        ExperienceTabRowByService(
            paddingValues = PaddingValues(),
            navController = rememberNavController(),
            id = dataExperienceTabRowByService.id
        )
    }
}