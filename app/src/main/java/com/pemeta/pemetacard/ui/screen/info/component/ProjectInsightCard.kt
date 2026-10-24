package com.pemeta.pemetacard.ui.screen.info.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.newsinfo.data.DataNewsInfo
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun LazyItemScope.ProjectInsightCard(
    headline: NewsInfo,
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(180.dp)
            .fillParentMaxWidth(0.75f)
            .clickable { navController.navigate(ScreenMenu.DetailInfo.createRoute(infoId = headline.id)) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
            modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(headline.image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Text(
                text = headline.headlineTitle?.uppercase() ?: "",
                modifier.padding(top = 140.dp, start = 8.dp),
                fontSize = 12.sp,
                maxLines = 2,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ProjectInsightCardPreview(){
    PemetaCardTheme {
        LazyRow {
            item {
                ProjectInsightCard(
                    headline = DataNewsInfo.NewsInfoList[0],
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ProjectInsightCard2Preview(){
    PemetaCardTheme {
        LazyRow {
            item {
                ProjectInsightCard(
                    headline = DataNewsInfo.NewsInfoList[1],
                )
            }
        }
    }
}