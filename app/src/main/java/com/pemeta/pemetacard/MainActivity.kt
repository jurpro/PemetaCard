package com.pemeta.pemetacard

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.pemeta.pemetacard.ui.screen.PemetaCard
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            PemetaCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PemetaCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun GreetingPreview() {
    PemetaCardTheme {
        PemetaCard()
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun GreetingPreviewTablet() {
    PemetaCardTheme {
        PemetaCard()
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun GreetingPreviewDesktop() {
    PemetaCardTheme {
        PemetaCard()
    }
}