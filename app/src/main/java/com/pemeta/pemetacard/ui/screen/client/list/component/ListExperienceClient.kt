package com.pemeta.pemetacard.ui.screen.client.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.ui.screen.experience.list.component.ItemExperienceCard
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListExperienceClient(
    modifier: Modifier = Modifier,
    navController: NavController,
    experiences: List<CompanyExperience>,
) {
    Column(modifier = modifier) {

        Spacer(modifier = Modifier.height(4.dp))

        experiences.forEachIndexed { _, experience ->
            ItemExperienceCard(
                experience = experience,
                navController = navController
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListExperienceHomePreview(){
    PemetaCardTheme {

        val listExperienceDataPreview = DataPemetaExperience.CompanyExperienceList

        ListExperienceClient(
            experiences = listExperienceDataPreview,
            navController = rememberNavController()
        )
    }
}