package com.pemeta.pemetacard.ui.screen.experience.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.address.data.LocationData
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.model.JobType
import com.pemeta.pemetacard.model.pengalaman.model.ServiceBudget
import com.pemeta.pemetacard.ui.screen.experience.detail.periodValue
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ItemExperienceInfoCard(
    title: String,
    type: JobType,
    serviceBudget: ServiceBudget,
    period: String,
    location: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            modifier = modifier.padding(0.dp, 0.dp, 12.dp, 8.dp),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge,
        )

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Column(modifier = modifier
                .align(Alignment.Top)
               // .padding(top = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.Bottom) {

                    val locationIcon: Painter = painterResource(id = R.drawable.ic_location_project)

                    Icon(
                        painter = locationIcon,
                        contentDescription = null,
                        modifier = modifier
                            .size(16.dp, 16.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color.Red
                    )

                    Text(
                        text = location,
                        modifier = modifier
                            .align(Alignment.Top)
                            .padding(8.dp, 8.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = period,
                    modifier = modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Column(
                modifier = modifier
                    .align(Alignment.Top)
                    .padding(8.dp, 8.dp, 8.dp, 0.dp),
                verticalArrangement = Arrangement.Top
            ) {
                ServiceBudgetTypeTag(
                    serviceBudget = serviceBudget,
                    navController = navController
                )

                Spacer(modifier = modifier.height(8.dp))

                ExperienceTag(
                    experienceType = type,
                    navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemExperienceInfoCardPreview(){
    PemetaCardTheme {
        val experienceInfoDataPreview = DataPemetaExperience.CompanyExperienceList[0]
        val periodValue = periodValue(experienceInfoDataPreview)
        val jobLocation: List<String> = experienceInfoDataPreview.location.map { key ->
            LocationData.provinceData[key] ?: "Lokasi Tidak Ada ($key)"
        }
        val location = jobLocation.joinToString ( ",\n" )

        ItemExperienceInfoCard(
            title = experienceInfoDataPreview.title,
            type = experienceInfoDataPreview.type,
            serviceBudget = experienceInfoDataPreview.serviceBudget,
            period = periodValue,
            location = location,
            navController = rememberNavController()
        )
    }
}