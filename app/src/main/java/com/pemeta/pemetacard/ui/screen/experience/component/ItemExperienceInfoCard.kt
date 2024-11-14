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
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ItemExperienceInfoCard(
    title: String,
    type: String,
    serviceBudget: String,
    period: String,
    location: String
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        Column(modifier = Modifier
            .align(Alignment.CenterVertically)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 8.dp),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge,
            )

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Column(modifier = Modifier
                    .align(Alignment.Top)
                    .padding(top = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {

                        val locationIcon: Painter = painterResource(id = R.drawable.ic_location_project)

                        Icon(
                            painter = locationIcon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp, 16.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Red
                        )

                        Text(
                            text = location,
                            modifier = Modifier
                                .align(Alignment.Top)
                                .padding(8.dp, 12.dp, 12.dp, 0.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = period,
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp, 0.dp, 8.dp, 0.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        ServiceBudgetTypeTag(serviceBudget)

                        Spacer(modifier = Modifier.height(8.dp))

                        ExperienceTag(type)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemExperienceInfoCardPreview(){
    PemetaCardTheme {

        val ExperienceInfoDataPreview = DataPemetaExperience.CompanyExperienceList[0]

        ItemExperienceInfoCard(
            title = ExperienceInfoDataPreview.title,
            type = ExperienceInfoDataPreview.type,
            serviceBudget = ExperienceInfoDataPreview.serviceBudget,
            period = ExperienceInfoDataPreview.period,
            location = ExperienceInfoDataPreview.location
        )
    }
}