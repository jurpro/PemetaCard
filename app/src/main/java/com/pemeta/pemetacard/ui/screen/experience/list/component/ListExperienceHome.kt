package com.pemeta.pemetacard.ui.screen.experience.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListExperienceHome(
    modifier: Modifier = Modifier,
    navController: NavController,
    experiences: List<CompanyExperience>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // Gunakan Column biasa jika komponen di atasnya sudah memiliki modifier verticalScroll()
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        experiences.forEach { experience ->
            key(experience.id) { // Pengganti key di LazyColumn agar recomposition efisien
                ItemExperienceCard(
                    experience = experience,
                    navController = navController
                )
            }
        }
    }

   /* Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(4.dp))

        experiences.forEachIndexed { _, experience ->
            ItemExperienceCard(
                experience = experience,
                navController = navController
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    } */
}

@Preview(showBackground = true)
@Composable
fun ListExperienceHomePreview(){
    PemetaCardTheme {

        val listExperienceDataPreview = DataPemetaExperience.CompanyExperienceList

        ListExperienceHome(
            experiences = listExperienceDataPreview,
            navController = rememberNavController()
        )
    }
}