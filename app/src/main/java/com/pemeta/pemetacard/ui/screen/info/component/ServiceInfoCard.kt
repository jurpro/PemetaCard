package com.pemeta.pemetacard.ui.screen.info.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.EncodeSans
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import com.pemeta.pemetacard.ui.theme.WarmGold

@Composable
fun ServiceInfoCard(
    icon: String,
    title: String,
    id: Int,
    description: String,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { navController.navigate(ScreenMenu.DetailType.createRoute(typeId = id)) },
           /* .clickable {
                Toast.makeText(
                    context,
                    // R.string.clickable_dev_detail,
                    "This is $title experices, come and see periodically for any updates",
                    Toast.LENGTH_LONG).show()
            }, */
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier
                    .width(30.dp)
                    .padding(start = 8.dp, top = 2.dp)
            ){
                Text(
                    text = icon,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    color = WarmGold
                )
            }
            Column(
                modifier
                    .weight(1f)
                   // .width(340.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = EncodeSans,
                    color = WarmGold
                )
                Text(
                    text = description,
                    fontFamily = EncodeSans,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier
                    .width(30.dp)
                    .padding(top = 8.dp, end = 8.dp)
            ){
                Icon(
                    imageVector = Icons.Default.DoubleArrow,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ServiceInfoCardPreview(){
    PemetaCardTheme {
        ServiceInfoCard(
            icon = "\u25C8",
            id = 1,
            title = "Brand Strategy",
            description = "We define the direction, positioning, and voice that separates your brand from the noise.",
            navController = rememberNavController()
        )
    }
}