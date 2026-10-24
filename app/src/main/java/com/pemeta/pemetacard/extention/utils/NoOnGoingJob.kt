package com.pemeta.pemetacard.extention.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun NoOnGoingJob(
    modifier: Modifier = Modifier
){
    Column(
        modifier
            .fillMaxWidth()
            .height(150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tidak ada pekerjaan yang sedang on going pada jenis pengalaman ini.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            fontSize = 18.sp,
            fontFamily = FontFamily.Cursive
        )
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun NoOnGoingJobPreview(){
    PemetaCardTheme {
        NoOnGoingJob()
    }
}