package com.pemeta.pemetacard.ui.screen.experience.ongoing.component

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
fun ListExperienceOnGoing(
    modifier: Modifier = Modifier,
    navController: NavController,
    experiences: List<CompanyExperience>
) {
    Column(modifier = modifier) {
       /* Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_experiences_more),
            )

            Text(
                text = stringResource(id = R.string.list_onGoing),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        } */

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
fun ListExperienceOnGoingPreview(){
    PemetaCardTheme {

        val listExperienceDataPreview = DataPemetaExperience.CompanyExperienceList

        ListExperienceOnGoing(
            experiences = listExperienceDataPreview,
            navController = rememberNavController()
        )
    }
}