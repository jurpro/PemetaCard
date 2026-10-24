package com.pemeta.pemetacard.ui.screen.experience.service

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.pengalaman.data.DataJobFinalType
import com.pemeta.pemetacard.model.pengalaman.data.DataJobType.JobTypeList
import com.pemeta.pemetacard.model.pengalaman.data.ServicesData
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRowByService
import com.pemeta.pemetacard.ui.screen.experience.component.tabrow.ExperienceTabRowByType
import com.pemeta.pemetacard.ui.screen.experience.typeJob.component.ExperienceByTypeViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceByServiceScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    id: Int,
    modifier: Modifier = Modifier,
    listExperienceByServiceViewModel: ListServiceViewModel = viewModel(factory = ViewModelFactory(
        Injection.provideRepository())
    ),
){
    // val context = LocalContext.current
    val serviceDetailData = ServicesData.listServicesData[id-1]

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(serviceDetailData.name,
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
                    serviceDetailData.apply {
                        Text(
                            text = description,
                            textAlign = TextAlign.Center,
                            modifier = modifier.padding(8.dp)
                        )
                    }
                }

                //List Experiences filtered by Job Type
                item {
                    Spacer(modifier = modifier.padding(8.dp))

                    ExperienceTabRowByService(
                        paddingValues = paddingValues,
                        navController = navController,
                        id = serviceDetailData.id,
                        listExperienceByServiceViewModel = listExperienceByServiceViewModel
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
@Preview(showBackground = true)
fun ExperienceByServiceScreenPreview(){
    PemetaCardTheme {
        val serviceDetailDataPreview = ServicesData.listServicesData[0]
        ExperienceByServiceScreen(
            id = serviceDetailDataPreview.id,
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}