package com.pemeta.pemetacard.ui.screen.info.component

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.extention.utils.LocalDateParsing
import com.pemeta.pemetacard.model.newsinfo.data.DataNewsInfo
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.screen.experience.component.ExperienceTag
import com.pemeta.pemetacard.ui.screen.experience.component.ServiceBudgetTypeTag
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ItemNewsCard(
    newsInfo: NewsInfo,
    navController: NavController
){
    val context = LocalContext.current
    val glassBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.25f),
            Color.White.copy(alpha = 0.1f)
        )
    )

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .background(glassBrush)
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
           // .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
            .clickable { navController.navigate(ScreenMenu.DetailInfo.createRoute(infoId = newsInfo.id)) },
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(newsInfo.image)
                    .crossfade(true).build(),
                contentDescription = newsInfo.code,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = newsInfo.code.toString(),
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = newsInfo.title,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )

                val localDate = LocalDateParsing().convertLocalDate(newsInfo.date.toString())

                Text(
                    text = localDate,
                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemNewsCardPreview(){
    PemetaCardTheme {
        val dataNewsInfoPreview = DataNewsInfo.NewsInfoList
        ItemNewsCard(
            newsInfo = dataNewsInfoPreview[0],
            navController = rememberNavController()
        )
    }
}