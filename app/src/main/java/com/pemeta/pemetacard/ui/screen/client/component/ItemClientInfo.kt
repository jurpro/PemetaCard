package com.pemeta.pemetacard.ui.screen.client.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ItemClientInfo(
    name: String,
    bio: String,
    address: String,
    image: Int
) {
    val context = LocalContext.current

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true).build(),
            contentDescription = name,
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
                text = name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = bio,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = address,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemClientInfoCardPreview(){
    PemetaCardTheme {

        val clientImagePreview= DataPemetaClient.ClientList[1]

        ItemClientInfo(
            name = clientImagePreview.name,
            bio = clientImagePreview.bio,
            address = clientImagePreview.address,
            image = clientImagePreview.image
        )
    }
}