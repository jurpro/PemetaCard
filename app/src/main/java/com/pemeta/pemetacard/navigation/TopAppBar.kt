package com.pemeta.pemetacard.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PemetaTopAppBar(
    @StringRes titleRes: Int,
  //  navigationIcon: ImageVector,
    navigationIconContentDescription: String,
  //  actionIcon: ImageVector,
    actionIconContentDescription: String,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    /*
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier.testTag("PemetaCardTopAppBar"),
    ) */
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PemetaCardTopAppBarPreview() {
    PemetaCardTheme {
        PemetaTopAppBar(
            titleRes = R.string.untitled,
            // navigationIcon = PemetaIcons.Search,
            navigationIconContentDescription = "Navigation icon",
           // actionIcon = PemetaIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}