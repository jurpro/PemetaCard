package com.pemeta.pemetacard.ui.screen.info.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.newsinfo.data.DataNewsInfo
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import com.pemeta.pemetacard.ui.screen.info.component.ItemNewsCard
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListNewsInfo(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    newsInfos: List<NewsInfo>
){
    Column(modifier = modifier.fillMaxWidth()){

        newsInfos.forEachIndexed { _, news ->
            ItemNewsCard(
                newsInfo = news,
                navController = navController
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListNewsInfoPreview(){
    PemetaCardTheme {

        val datanewsInfosPreview = DataNewsInfo.NewsInfoList

        ListNewsInfo(
            newsInfos = datanewsInfosPreview
        )
    }
}