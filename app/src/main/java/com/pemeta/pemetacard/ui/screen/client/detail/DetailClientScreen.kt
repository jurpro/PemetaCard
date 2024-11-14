package com.pemeta.pemetacard.ui.screen.client.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.DataPemetaClient
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.detail.Title
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailClientScreen(
    navController: NavController,
    id: Int,
   // modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val clientDetailData = DataPemetaClient.ClientList[id-1]

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
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable { navController.popBackStack() },
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
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
                    clientDetailData.apply {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(clientDetailData.image)
                                .crossfade(true).build(),
                            contentDescription = clientDetailData.name,
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                            // .height(346.dp)
                            // .clip(shape = RoundedCornerShape(16.dp))
                        )

                        // Spacer(modifier.height(18.dp))
                    }
                }

                // Client info
                item {
                    clientDetailData.apply {
                        // Spacer(modifier = Modifier.height(12.dp))
                        Title(title = stringResource(id = R.string.detail_experience_info_card))
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 0.dp, 8.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_year_begin), value = experienceData.yearBegin.toString())
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_year_end), value = experienceData.yearEnd.toString())
                            ExperienceInfoLongVersionCard(
                                title = stringResource(id = R.string.detail_client_code),
                                value = clientDetailData.code
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            ExperienceInfoLongVersionCard(
                                title = stringResource(id = R.string.detail_client_address),
                                value = clientDetailData.address
                            )
                            // ExperienceInfoLongVersionCard(title = stringResource(id = R.string.detail_experience_partner), value = experienceData.partner)
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_animal_info_health), value = health)
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_period), value = experienceData.period.toString().plus(" Kg"))
                        }
                    }
                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailClientScreenPreview(){
    PemetaCardTheme {
        DetailClientScreen(
            navController = rememberNavController(),
            id = 1
        )
    }
}