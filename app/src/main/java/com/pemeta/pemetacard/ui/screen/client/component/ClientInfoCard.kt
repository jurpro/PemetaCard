package com.pemeta.pemetacard.ui.screen.client.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.Client
import com.pemeta.pemetacard.model.CompanyExperience
import com.pemeta.pemetacard.model.DataPemetaClient
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ClientInfoCard(
    client: Client,
    navController: NavController,
    // id: Int
) {
    val context = LocalContext.current

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
            // .clickable { navController.navigate(ScreenMenu.DetailClient.createRoute(clientId = client.id)) },
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(client.image)
                    .crossfade(true).build(),
                contentDescription = client.code,
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier) {
                Text(
                    text = client.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = client.bio,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = client.address,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClientInfoCardPreview(){
    PemetaCardTheme {

        val clientImagePreview= DataPemetaClient.ClientList

        ClientInfoCard(
            client = clientImagePreview[1],
            navController = rememberNavController()
        )
    }
}