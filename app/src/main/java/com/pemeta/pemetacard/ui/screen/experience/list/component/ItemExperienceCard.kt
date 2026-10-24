package com.pemeta.pemetacard.ui.screen.experience.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceTag
import com.pemeta.pemetacard.ui.screen.experience.component.ServiceBudgetTypeTag
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ItemExperienceCard(
    experience: CompanyExperience,
    navController: NavController
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
           // .height(120.dp)
          //  .width(500.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { navController.navigate(ScreenMenu.DetailExperience.createRoute(id = experience.id)) },
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
               // .background(MaterialTheme.colorScheme.surface, CircleShape),
            verticalAlignment = Alignment.CenterVertically
          /*  modifier = Modifier
               // .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween */
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(experience.image)
                    .crossfade(true).build(),
                contentDescription = experience.title,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )

          //  Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = experience.code,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = experience.title,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                    maxLines = 3,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
            
          //  Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                   // .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                   // .padding(8.dp, 0.dp, 0.dp, 0.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                ServiceBudgetTypeTag(
                    serviceBudget = experience.serviceBudget,
                    navController = navController
                )

                Spacer(modifier = Modifier.height(6.dp))

                ExperienceTag(
                    experienceType = experience.type,
                    navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemExperienceCardPreview(){
    PemetaCardTheme {

        val experienceDataPreview = DataPemetaExperience.CompanyExperienceList[0]

        ItemExperienceCard(
            experience = experienceDataPreview,
            navController = rememberNavController()
        )
    }
}