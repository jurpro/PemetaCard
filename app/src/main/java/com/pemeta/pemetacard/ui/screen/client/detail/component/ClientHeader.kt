package com.pemeta.pemetacard.ui.screen.client.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.client.Client
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceInfoLongVersionCard
import com.pemeta.pemetacard.ui.screen.experience.detail.Title
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ClientHeader(
    clientDetailData: Client,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(clientDetailData.image)
                .crossfade(true)
                .build(),
            contentDescription = clientDetailData.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(150.dp)
                .padding(vertical = 8.dp)
        )

        Title(title = stringResource(id = R.string.detail_experience_info_card))

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExperienceInfoLongVersionCard(
                title = stringResource(id = R.string.detail_client_code),
                value = clientDetailData.code
            )
            Spacer(modifier = Modifier.width(8.dp))
            ExperienceInfoLongVersionCard(
                title = stringResource(id = R.string.detail_client_address),
                value = clientDetailData.address
            )
        }
     //   Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ClientHeaderPreview(){
    PemetaCardTheme {
        val clientPreview = DataPemetaClient.ClientList[2]
        ClientHeader(
            clientDetailData = clientPreview
        )
    }
}