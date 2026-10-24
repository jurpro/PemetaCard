package com.pemeta.pemetacard.ui.screen.team.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.team.Team
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.screen.experience.detail.Title
import com.pemeta.pemetacard.ui.screen.team.component.AboutEmployee
import com.pemeta.pemetacard.ui.screen.team.component.BiodataEmployee
import com.pemeta.pemetacard.ui.screen.team.component.CurriculumVitaePemeta
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTeamScreen(
    id: Int,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    val dataTeamManagement = Team.teamManagement[id-1]

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(dataTeamManagement.position,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
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
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = ScreenMenu.Info.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
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
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface),
            ) {
                // Basic details
                item {
                    dataTeamManagement.apply {
                        Image(
                            painter = painterResource(photo),
                            contentDescription = name,
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(346.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Team info
                item {
                    dataTeamManagement.apply {
                        // Spacer(modifier = Modifier.height(12.dp))
                        Title(title = "Biodata")
                        Spacer(modifier = modifier.height(8.dp))

                        BiodataEmployee(
                            employee = dataTeamManagement
                        )
                    }
                }

                item {
                    dataTeamManagement.apply {
                        Title(title = "Pengalaman Kerja Profesional")
                        Spacer(modifier = modifier.height(8.dp))

                        CurriculumVitaePemeta(
                            employee = dataTeamManagement
                        )
                    }
                }

                item {
                    dataTeamManagement.apply {
                        Title(title = "Sekilas tentang $nickName")
                        Spacer(modifier = modifier.height(8.dp))

                        AboutEmployee(
                            employee = dataTeamManagement
                        )
                    }
                }

                item {
                    Spacer(modifier.padding(top = 12.dp))
                    CopyrightPemeta()
                    Spacer(modifier.padding(
                        top = 12.dp,
                        bottom = 12.dp
                    ))
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun DetailTeamScreenPreview(){
    PemetaCardTheme {
        val dataEmployeePreview = Team.teamManagement[4]
        DetailTeamScreen(
            navController = rememberNavController(),
           // employee = dataEmployeePreview,
            id = dataEmployeePreview.id
        )
    }
}