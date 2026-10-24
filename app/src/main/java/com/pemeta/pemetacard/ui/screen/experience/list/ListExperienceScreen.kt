package com.pemeta.pemetacard.ui.screen.experience.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.pengalaman.data.DataEnumTabRowExperience
import com.pemeta.pemetacard.model.pengalaman.data.DataEnumTabRowExperienceType
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.PemetaCard
import com.pemeta.pemetacard.ui.screen.client.list.component.ListExperienceAll
import com.pemeta.pemetacard.ui.screen.client.list.component.ListExperienceFinished
import com.pemeta.pemetacard.ui.screen.client.list.component.ListExperienceOnGoing
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRow
import com.pemeta.pemetacard.ui.screen.experience.list.component.ListExperienceHome
import com.pemeta.pemetacard.ui.screen.experience.list.component.TopBar
import com.pemeta.pemetacard.ui.screen.experience.ongoing.component.OnGoingViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import kotlinx.coroutines.launch

@Composable
fun ListExperienceScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
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

        //List Experiences filtered by Type of Work = Planning and Supervision
        item {
            // Spacer(modifier = modifier.padding(8.dp))
            ExperienceTabRow(
                paddingValues = paddingValues,
                navController = navController,
            )
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

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ListExperienceScreenPreviewTablet() {
    PemetaCardTheme {
        ListExperienceScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ListExperienceScreenPreviewDesktop() {
    PemetaCardTheme {
        ListExperienceScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}