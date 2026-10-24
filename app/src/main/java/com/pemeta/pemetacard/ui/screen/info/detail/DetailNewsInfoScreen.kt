package com.pemeta.pemetacard.ui.screen.info.detail

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
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
import com.pemeta.pemetacard.model.newsinfo.data.DataNewsInfo
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoCard
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.component.ItemExperienceInfoCard
import com.pemeta.pemetacard.ui.screen.experience.detail.Title
import com.pemeta.pemetacard.ui.screen.info.list.ListNewsInfoViewModel
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNewsInfoScreen(
    navController: NavController,
    id: Int,
    modifier: Modifier = Modifier,
    listsNewsInfoViewModel: ListNewsInfoViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
){
    val newsData = DataNewsInfo.NewsInfoList[id-1]
    val context = LocalContext.current

    Scaffold(
        // Show the top app bar on top level destinations.
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(newsData.code.toString(),
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
        contentWindowInsets = WindowInsets(top = 0.dp)
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface),
        ) {
            // Basic details
            item {
                newsData.apply {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(newsData.image)
                            .crossfade(true).build(),
                        contentDescription = newsData.title,
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )

                   // Spacer(modifier.height(18.dp))

                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Description details
            item {
                newsData.apply {
                    Spacer(modifier = Modifier.height(12.dp))
                    Title(title = stringResource(R.string.detail_news_description))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 8.dp, 8.dp),
                        color = colorResource(id = R.color.text),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
            }

            // Basic info
            item {
                newsData.apply {
                    // Spacer(modifier = Modifier.height(12.dp))
                    Title(title = stringResource(id = R.string.detail_experience_info_card))
                    Spacer(modifier = modifier.height(8.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp, 0.dp, 8.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Spacer(modifier = Modifier.height(8.dp))

                        // ExperienceInfoCard(title = stringResource(id = R.string.detail_news_code), value = newsData.code)
                        // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_year_end), value = experienceData.yearEnd.toString())
                        ExperienceInfoLongVersionCard(
                            title = stringResource(id = R.string.detail_news_code),
                            value = newsData.project?.title ?: "No data available",
                            clickable = {
                                if (newsData.project != null) {
                                    navController.navigate(ScreenMenu.DetailExperience.createRoute(id = newsData.project.id))
                                } else {
                                    Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show()
                                }
                            }
                        )

                        Spacer(modifier = modifier.width(8.dp))

                        val localDate = LocalDateParsing().convertLocalDate(newsData.date.toString())

                        ExperienceInfoLongVersionCard(
                            title = stringResource(id = R.string.detail_news_date),
                            value = localDate,
                            clickable = { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() }
                        )

                        // ExperienceInfoLongVersionCard(title = stringResource(id = R.string.detail_experience_partner), value = experienceData.partner)
                        // ExperienceInfoCard(title = stringResource(id = R.string.detail_animal_info_health), value = health)
                        // ExperienceInfoCard(title = stringResource(id = R.string.detail_experience_period), value = experienceData.period.toString().plus(" Kg"))
                    }
                }
            }

            //Copyright
            item {
                Spacer(modifier = Modifier.height(8.dp))
                CopyrightPemeta()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailNewsInfoScreenPreview(){
    PemetaCardTheme {

        val newsData = DataNewsInfo.NewsInfoList[0]

        DetailNewsInfoScreen(
            id = newsData.id,
            navController = rememberNavController()
        )
    }
}