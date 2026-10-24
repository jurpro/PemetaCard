package com.pemeta.pemetacard.ui.screen.experience.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.extention.utils.LocalDateParsing
import com.pemeta.pemetacard.model.UiState
import com.pemeta.pemetacard.model.address.data.LocationData
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.client.component.ClientInfoCard
import com.pemeta.pemetacard.ui.screen.client.list.component.ListClientViewModel
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.component.ItemExperienceInfoCard
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfo
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfoViewModel
import com.pemeta.pemetacard.ui.screen.maps.component.MapsProjectPemeta
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.collections.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailExperienceScreen(
    navController: NavController,
    id: Int,
    listsNewsInfoViewModel: ListNewsInfoViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
){
    val context = LocalContext.current
   // val experienceData = DataPemetaExperience.CompanyExperienceList[id-1]
    val experienceData = remember(id) { DataPemetaExperience.CompanyExperienceList[id-1] }

    // KSO
    val partnerValue = valuePartner(experienceData)
    //Tanggal
    val periodValue = periodValue(experienceData)
    //Nominal Kontrak
    val localeID = Locale.Builder().setLanguage("id").setRegion("ID").build()
    val valueContract = NumberFormat.getCurrencyInstance(localeID).format(experienceData.contractAmount)
    //Mapping Lokasi Pekerjaan
    val jobLocation: List<String> = experienceData.location.map { key ->
        LocationData.provinceData[key] ?: "Lokasi Tidak Ada ($key)"
    }
    val location = jobLocation.joinToString ( ",\n" )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(experienceData.code,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
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
    )
    { innerPadding ->
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
                            .data(image)
                            .crossfade(true).build(),
                        contentDescription = title,
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxWidth()
                            // .height(346.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ItemExperienceInfoCard(title, type, serviceBudget, periodValue, location, navController)
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
                            .padding(16.dp, 0.dp, 8.dp, 8.dp),
                        color = colorResource(id = R.color.text),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start
                    )
                }
            }

            // Experience info
            item {
                experienceData.apply {
                    Title(title = stringResource(id = R.string.detail_experience_info_card))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 0.dp, 8.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ExperienceInfoLongVersionCard(
                            title = stringResource(id = R.string.detail_experience_period),
                            value = periodValue
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        ExperienceInfoLongVersionCard(
                            title = stringResource(id = R.string.detail_experience_amount),
                            value = valueContract
                        )
                    }
                }
            }

            // Partner / KSO details
            item {
                experienceData.apply {
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 0.dp, 8.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ExperienceInfoLongVersionCard(
                            title = stringResource(id = R.string.detail_experience_partner),
                            value = partnerValue,
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        ExperienceInfoLongVersionCard(
                            title = stringResource(R.string.detail_experience_contract_number),
                            value = experienceData.contractNumber
                        )
                    }
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
                            navController = navController
                            // name, bio, address, image
                        )
                    }
                }
            }

            // Maps Lokasi Pekerjaan
            item {
                Spacer(modifier = Modifier.height(12.dp))
                Title(title = stringResource(R.string.detail_experience_maps))
                Spacer(modifier = Modifier.height(8.dp))

                MapsProjectPemeta(
                    id = experienceData.id
                )
            }

            // List Berita / Kegiatan Proyek ini
            item {
                experienceData.apply {
                    Spacer(modifier = Modifier.height(12.dp))

                    listsNewsInfoViewModel.uiStateNewsByExperience.collectAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> { listsNewsInfoViewModel.getListNewsByExperiences(experienceId = experienceData.id) }
                            is UiState.Success -> {
                                if (!uiState.data.isEmpty()) {
                                    Title(title = stringResource(R.string.detail_experience_news_info))
                                    Spacer(modifier = Modifier.height(8.dp))
                                    ListNewsInfo(
                                        modifier = Modifier.padding(
                                            start = 16.dp,
                                            end = 16.dp,
                                            //  bottom = paddingValues.calculateBottomPadding()
                                        ),
                                        navController = navController,
                                        newsInfos = uiState.data
                                    )
                                }
                            }
                            is UiState.Error -> { Text(text = stringResource(id = R.string.error)) }
                        }
                    }
                }
            }

            //Copyright
            item {
                Spacer(modifier = Modifier.height(8.dp))
                CopyrightPemeta()
                Spacer(modifier = Modifier
                    .padding(bottom = innerPadding.calculateBottomPadding())
                )
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Start
    )
}


@Preview(showBackground = true)
@Composable
fun DetailExperiencePreview() {
    PemetaCardTheme {
        DetailExperienceScreen(
            navController = rememberNavController(),
            id = 5
        )
    }
}