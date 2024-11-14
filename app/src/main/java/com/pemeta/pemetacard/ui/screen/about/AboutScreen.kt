package com.pemeta.pemetacard.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.ui.screen.maps.MapsOfficePemeta
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        /* Image(
             painter = painterResource(R.drawable.tarucing_hut_ri_77),
             contentDescription = null,
             contentScale = ContentScale.FillWidth,
             alignment = Alignment.Center,
             modifier = Modifier.padding(0.dp)
         ) */

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo_pemeta_2024_hitam_baru),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .border(4.dp, Color.Green, CircleShape)
                    .clip(CircleShape)
                    .size(150.dp)
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
            )

            Text(
                text = stringResource(R.string.company_full_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(8.dp)
            )
            MapsOfficePemeta(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.company_address),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.company_phone),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                // modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.company_email),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                // modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.company_inkindo),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                // modifier = Modifier.padding(8.dp)
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.pemeta_copyright),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun AboutScreenPreview(){
    PemetaCardTheme {
        AboutScreen()
    }
}