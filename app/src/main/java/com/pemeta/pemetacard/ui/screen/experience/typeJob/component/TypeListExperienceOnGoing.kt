package com.pemeta.pemetacard.ui.screen.experience.typeJob.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.ui.screen.experience.list.component.ItemExperienceCard
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun TypeListExperienceOnGoing(
    modifier: Modifier = Modifier,
    navController: NavController,
    experiencesTypeOnGoing: List<CompanyExperience>,
){
    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(4.dp))

        experiencesTypeOnGoing.forEachIndexed { _, experience ->
            ItemExperienceCard(
                experience = experience,
                navController = navController
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun TypeListExperienceOnGoingPreview(){
    PemetaCardTheme {
        val listExperienceDataPreview = DataPemetaExperience.CompanyExperienceList
        TypeListExperienceOnGoing(
            experiencesTypeOnGoing = listExperienceDataPreview,
            navController = rememberNavController()
        )
    }
}