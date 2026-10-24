package com.pemeta.pemetacard.ui.screen.legal.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.model.lagilitas.data.DataLegalitas
import com.pemeta.pemetacard.model.lagilitas.model.Legalitas
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ItemLegalitasCard(
    legalitas: Legalitas,
    navController: NavController,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
           // .fillMaxWidth()
            .width(100.dp)
            .height(135.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(5.dp))
            .clickable { navController.navigate(ScreenMenu.DetailLegalitas.createRoute(legalitasId = legalitas.id)) },
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ){
        Column(
            modifier = Modifier
               // .width(84.dp)
               // .height(98.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
               // .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(legalitas.icon)
                    .crossfade(true).build(),
                contentDescription = legalitas.code,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(80.dp)
                    .height(90.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = legalitas.code,
                modifier = Modifier
                    // .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.displaySmall,
            )

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemLegalitasCardPreview(){
    PemetaCardTheme {

        val dataLegalitasCard = DataLegalitas.LegalitasList[0]

        ItemLegalitasCard(
            legalitas = dataLegalitasCard,
            navController = rememberNavController()
        )
    }
}