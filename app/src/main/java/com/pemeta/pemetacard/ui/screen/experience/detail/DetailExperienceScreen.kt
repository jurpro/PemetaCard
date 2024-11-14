package com.pemeta.pemetacard.ui.screen.experience.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.Client
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.screen.client.component.ClientInfoCard
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoCard
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.component.ItemExperienceInfoCard
import com.pemeta.pemetacard.ui.screen.experience.list.component.ItemExperienceCard
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailExperienceScreen(
    navController: NavController,
    id: Int,
){
    val context = LocalContext.current
    val experienceData = DataPemetaExperience.CompanyExperienceList[id-1]

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(experienceData.code,
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
                            .clickable { navController.navigateUp() },
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
                    experienceData.apply {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(experienceData.image)
                                .crossfade(true).build(),
                            contentDescription = experienceData.title,
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                // .height(346.dp)
                               // .clip(shape = RoundedCornerShape(16.dp))
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        ItemExperienceInfoCard(title, type, serviceBudget, period, location)
                    }
                }

                // Experience info
                item {
                    experienceData.apply {
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
                                title = stringResource(id = R.string.detail_experience_period),
                                value = experienceData.period
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            ExperienceInfoLongVersionCard(
                                title = stringResource(id = R.string.detail_experience_amount),
                                value = experienceData.contractAmount
                            )
                           // ExperienceInfoLongVersionCard(title = stringResource(id = R.string.detail_experience_partner), value = experienceData.partner)
                           // ExperienceInfoCard(title = stringResource(id = R.string.detail_animal_info_health), value = health)
                            // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_period), value = experienceData.period.toString().plus(" Kg"))
                        }
                    }
                }

                // Partner / KSO details
                item {
                    experienceData.apply {
                        Spacer(modifier = Modifier.height(12.dp))
                        // Title(title = stringResource(R.string.detail_experience_partner))
                       // Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 0.dp, 8.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ExperienceInfoLongVersionCard(
                                title = stringResource(id = R.string.detail_experience_partner),
                                value = experienceData.partner
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            ExperienceInfoLongVersionCard(
                                title = stringResource(R.string.detail_experience_contract_number),
                                value = experienceData.contractNumber
                            )
                        }
                    }
                }
                
                // Description details
                item {
                    experienceData.apply {
                        Spacer(modifier = Modifier.height(12.dp))
                        Title(title = stringResource(R.string.detail_experience_description))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp),
                            color = colorResource(id = R.color.text),
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                // Owner info
                item {
                    experienceData.apply {
                        Spacer(modifier = Modifier.height(12.dp))
                        Title(title = stringResource(R.string.detail_experience_client))
                        Spacer(modifier = Modifier.height(8.dp))
                        ourClient.apply {
                            ClientInfoCard(
                                client = ourClient,
                                navController = rememberNavController()
                                // name, bio, address, image
                            )
                        }
                    }
                }

                // Button Multi function
              /*  item {
                    Spacer(modifier = Modifier.height(36.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(16.dp, 0.dp, 16.dp, 0.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        onClick = { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
                    ) {
                        Text(stringResource(id = R.string.detal_animal_button_play))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                } */

                //Copyright
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.pemeta_copyright),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    )
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}


@Preview(showBackground = true)
@Composable
fun DetailExperiencePreview() {
    PemetaCardTheme {
        DetailExperienceScreen(
            navController = rememberNavController(),
            id = 1
        )
    }
}