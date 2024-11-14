package com.pemeta.pemetacard.ui.screen.experience.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.CompanyExperience
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListExperienceHome(
    modifier: Modifier = Modifier,
    navController: NavController,
    experiences: List<CompanyExperience>
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_experiences_more),
            )

            Text(
                text = stringResource(id = R.string.list_experiences),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        }

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

        ListExperienceHome(
            experiences = listExperienceDataPreview,
            navController = rememberNavController()
        )
    }
}