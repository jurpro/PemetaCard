package com.pemeta.pemetacard.ui.screen.experience.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun DetailExperience_1(
    modifier: Modifier = Modifier
) {
    val experienceData = DataPemetaExperience.CompanyExperienceList[1]

    Box{
        /*  Image(
              painter = painterResource(experienceData.image),
              contentDescription = null,
              modifier
                  .fillMaxSize()
                  .padding(16.dp)
                  .graphicsLayer { this.alpha = 0.25f }
          ) */

        Column(
            modifier.padding(16.dp),
        ) {
            Image(
                painter = painterResource(experienceData.image),
                contentDescription = null,
                modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier.height(18.dp))

            Row {
                Text(
                    text = "Pekerjaan : "
                )
                Spacer(
                    modifier = Modifier.width(36.dp)
                )
                Text(
                    text = experienceData.title
                )
            }
            Row {
                Text(
                    text = "Jenis : "
                )
                Spacer(
                    modifier = Modifier.width(48.dp)
                )
                Text(
                    text = experienceData.type
                )
            }
            Row {
                Text(
                    text = "Nilai Kontrak : "
                )
                Spacer(
                    modifier = Modifier.width(32.dp)
                )
                Text(
                    text = experienceData.contractAmount
                )
            }
            Row {
                Text(
                    text = "Anggaran : "
                )
                Spacer(
                    modifier = Modifier.width(38.dp)
                )
                Text(
                    text = experienceData.serviceBudget
                )
                Text(
                    text = " TA "
                )
                Text(
                    text = experienceData.yearBegin.toString()
                )
                Text(
                    text = " - "
                )
                Text(
                    text = experienceData.yearEnd.toString()
                )
            }
            Row {
                Text(
                    text = "Partner : "
                )
                Spacer(
                    modifier = Modifier.width(38.dp)
                )
                Text(
                    text = experienceData.partner
                )
            }
            Row {
                Text(
                    text = "Satker/Dinas : "
                )
                Spacer(
                    modifier = Modifier.width(18.dp)
                )
                Text(
                    text = experienceData.ourClient.name
                )
            }
            Row {
                Text(
                    text = "Lokasi : "
                )
                Spacer(
                    modifier = Modifier.width(18.dp)
                )
                Text(
                    text = experienceData.location
                )
            }
            Row {
                Text(
                    text = "Periode : "
                )
                Spacer(
                    modifier = Modifier.width(18.dp)
                )
                Text(
                    text = experienceData.period
                )
            }
            Row {
                Text(
                    text = "Deskripsi : "
                )
                Spacer(
                    modifier = Modifier.width(18.dp)
                )
                Text(
                    text = experienceData.description
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailExperience1Preview() {
    PemetaCardTheme {
        DetailExperience_1()
    }
}