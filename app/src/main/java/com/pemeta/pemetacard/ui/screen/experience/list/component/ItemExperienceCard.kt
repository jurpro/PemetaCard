package com.pemeta.pemetacard.ui.screen.experience.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.CompanyExperience
import com.pemeta.pemetacard.model.DataPemetaExperience
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
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { navController.navigate(ScreenMenu.DetailExperience.createRoute(id = experience.id)) },
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
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
                    .clip(
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier
                .width(150.dp)
               // .fillMaxHeight()
                .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = experience.code,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

              /*  Row(verticalAlignment = Alignment.Bottom) {
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
                        text = experience.location,
                        modifier = Modifier
                            .align(Alignment.Top)
                            .padding(8.dp, 0.dp, 12.dp, 4.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelMedium
                    )
                } */

                Text(
                    text = experience.title,
                    modifier = Modifier.padding(0.dp, 8.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp, 0.dp, 8.dp, 0.dp),
                   // .fillMaxHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                ServiceBudgetTypeTag(experience.serviceBudget)

                Spacer(modifier = Modifier.height(6.dp))

                ExperienceTag(experience.type)
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