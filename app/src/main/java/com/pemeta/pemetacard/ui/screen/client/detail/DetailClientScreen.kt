package com.pemeta.pemetacard.ui.screen.client.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.data.DataJobFinalType
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.client.detail.component.ClientHeader
import com.pemeta.pemetacard.ui.screen.client.list.component.ExperienceByClientViewModel
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRowByClient
import com.pemeta.pemetacard.ui.screen.experience.detail.Title
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfoViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

// @SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailClientScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    id: Int,
    modifier: Modifier = Modifier,
    listExperienceByClientViewModel: ExperienceByClientViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    listsNewsInfoViewModel: ListNewsInfoViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
){
   // val context = LocalContext.current
    val clientDetailData = DataPemetaClient.ClientList[id-1]
    val clientExperience = DataPemetaExperience.CompanyExperienceList
    val dataTitlesOnGoing = DataJobFinalType.JobFinalTypeList
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(clientDetailData.code,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    ) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = ScreenMenu.Experience.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                },
                windowInsets = WindowInsets(0,0,0,0)
            )
        },
    ) { innerPadding ->
        LazyColumn (
            contentPadding = innerPadding,
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Basic details
            item {
                ClientHeader(
                    clientDetailData = clientDetailData
                )
            }

            //List Experiences filtered by Client
            item {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    ExperienceTabRowByClient(
                        paddingValues = paddingValues,
                        navController = navController,
                        id = clientDetailData.id,
                        listExperienceByClientViewModel = listExperienceByClientViewModel
                    )
                }
            }

            //Copyright
            item {
                Spacer(modifier = modifier.height(8.dp))

                CopyrightPemeta()

                Spacer(modifier = modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailClientScreenPreview(){
    PemetaCardTheme {
        DetailClientScreen(
            navController = rememberNavController(),
            paddingValues = PaddingValues(),
            id = 2
        )
    }
}