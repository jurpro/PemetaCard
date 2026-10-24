package com.pemeta.pemetacard.ui.screen.info.contact

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import androidx.core.net.toUri
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.ui.screen.maps.MapsOfficePemeta
import com.pemeta.pemetacard.ui.theme.EncodeSans
import com.pemeta.pemetacard.ui.theme.WarmGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUs(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var whatsappNumber by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Scaffold(
        // Show the top app bar on top level destinations.
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Contact Us",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    ) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = ScreenMenu.Info.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                },
                windowInsets = WindowInsets(0,0,0,0)
            )
        },
    )
    { paddingValues ->
        Column(
            modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
           // horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Let's Build Something real.",
                modifier.padding(start = 8.dp),
                fontFamily = EncodeSans,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 28.sp,
                color = WarmGold
            )

            Spacer(modifier.padding(4.dp))
            // Alamat Pemeta
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


           /* Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_mark_email_read_24),
                    contentDescription = "",
                    modifier.padding(end = 8.dp)
                )

                Text(
                    text = "pemeta_bisnis@gmail.com",
                    fontFamily = EncodeSans,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )
            }

            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_contact_phone_24),
                    contentDescription = "",
                    modifier.padding(end = 8.dp)
                )

                Text(
                    text = "+62 22 7302423",
                    fontFamily = EncodeSans,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )

            }

            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_location_on_24),
                    contentDescription = "",
                    modifier.padding(end = 8.dp)
                )
                Text(
                    text = " Jl. Suryalaya Barat I No. 7 Bandung 40265",
                    fontFamily = EncodeSans,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )
            } */

            Text(
                text = "Silakan kirim pesan untuk menghubungi kami, tinggalkan alamat email atau nomor telepon Whatsapp sehingga kami dapat membalasnya di kemudian hari.",
                fontFamily = EncodeSans,
                // style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                // lineHeight = 30.sp,
            )


            Button(
                onClick = {
                    val messageSending = "Nama: $name \n " +
                            "Perusahaan: $company \n " +
                            "Email: $email \n " +
                            "Nomor Whatsapp: $whatsappNumber \n " +
                            "Pesan: $message"

                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:pemeta.bisnis@gmail.com".toUri()
                        putExtra(Intent.EXTRA_SUBJECT, "Pesan dari Aplikasi PemetaCard")
                        putExtra(Intent.EXTRA_TEXT, messageSending)
                    }
                    //  context.startActivity(Intent.createChooser(intent, "Pilih Aplikasi Email"))
                    context.startActivity(intent)
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Text("Send us a message")
            }

            CopyrightPemeta()
            Spacer(
                modifier
                    .padding(
                        top = 12.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ContactUsPreview(){
    PemetaCardTheme {
        ContactUs(
            navController = rememberNavController()
        )
    }
}