package com.pemeta.pemetacard.ui.screen.client.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pemeta.pemetacard.model.DataPemetaClient
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun DetailClient(
    modifier: Modifier = Modifier
){
    val clientDetail = DataPemetaClient.ClientList[0]

    Box(){
      /*  Image(
            painter = painterResource(clientDetail.image),
            contentDescription = null,
            modifier
                .fillMaxSize()
                .padding(16.dp)
                .graphicsLayer { this.alpha = 0.25f }
        ) */
        Column(modifier.padding(16.dp)) {
            Image(
                painter = painterResource(clientDetail.image),
                contentDescription = null,
                modifier
                    .align(Alignment.CenterHorizontally)
                    .height(175.dp)
            )
            Spacer(modifier.height(18.dp))

            Row {
                Image(
                    painter = painterResource(clientDetail.image),
                    contentDescription = null,
                    modifier
                        .width(48.dp)
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = clientDetail.name
                )
            }
            Row {
                Text(
                    text = clientDetail.address
                )
            }
            Row {
                Text(
                    text = clientDetail.bio
                )
            }
            Row {
                Text(
                    text = clientDetail.province
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailClientPreview(){
    PemetaCardTheme {
        DetailClient()
    }
}