package com.pemeta.pemetacard.ui.screen.experience.typeJob

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.model.pengalaman.data.DataJobFinalType
import com.pemeta.pemetacard.model.pengalaman.data.DataJobType.JobTypeList
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.client.list.component.ListClientViewModel
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRowByClient
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRowByType
import com.pemeta.pemetacard.ui.screen.experience.detail.Title
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.ExperienceByTypeViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceByTypeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    id: Int,
    modifier: Modifier = Modifier,
    listExperienceByTypeViewModel: ExperienceByTypeViewModel = viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
){
    // val context = LocalContext.current
    val typeDetailData = JobTypeList[id-1]
    val dataTitlesOnGoing = DataJobFinalType.JobFinalTypeList

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(typeDetailData.name,
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
                    IconButton(onClick = { navController.navigate(route = ScreenMenu.Info.route) }) {
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

        content = { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    // .fillMaxSize()
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface),
            ) {
                // Basic details
                item {
                    typeDetailData.apply {
                        if (headListJob != null) {
                            Text(
                                text = headListJob,
                                textAlign = TextAlign.Center,
                                modifier = modifier.padding(8.dp)
                            )
                        }
                    }
                }
                // Type Job info
             /*   item {
                    typeDetailData.apply {
                        // Spacer(modifier = Modifier.height(12.dp))
                        Title(title = stringResource(id = R.string.detail_experience_info_card))
                        Spacer(modifier = modifier.height(8.dp))
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(8.dp, 0.dp, 8.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_year_begin), value = experienceData.yearBegin.toString())
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_year_end), value = experienceData.yearEnd.toString())
                            ExperienceInfoLongVersionCard(
                                title = stringResource(id = R.string.detail_client_code),
                                value = typeDetailData.code.toString()
                            )

                            Spacer(modifier = modifier.width(8.dp))

                            ExperienceInfoLongVersionCard(
                                title = stringResource(id = R.string.detail_client_address),
                                value = typeDetailData.name
                            )

                            // ExperienceInfoLongVersionCard(title = stringResource(id = R.string.detail_experience_partner), value = experienceData.partner)
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_animal_info_health), value = health)
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_period), value = experienceData.period.toString().plus(" Kg"))
                        }
                    }
                } */

                //List Experiences filtered by Job Type
                item {
                    Spacer(modifier = modifier.padding(8.dp))

                    ExperienceTabRowByType(
                        paddingValues = paddingValues,
                        navController = navController,
                        id = typeDetailData.id,
                        listExperienceByTypeViewModel = listExperienceByTypeViewModel
                    )
                }

                //Copyright
                item {
                    Spacer(modifier = modifier.height(8.dp))

                    CopyrightPemeta()

                    Spacer(modifier = modifier.height(12.dp))
                }
            }

        }
    )
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ExperienceByTypeScreenPreview(){
    PemetaCardTheme {
        val typeDetailDataPreview = JobTypeList[2]
        ExperienceByTypeScreen(
            id = typeDetailDataPreview.id,
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}
