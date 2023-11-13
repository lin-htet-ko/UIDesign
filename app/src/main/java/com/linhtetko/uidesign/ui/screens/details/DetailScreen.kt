package com.linhtetko.uidesign.ui.screens.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.uidesign.R
import com.linhtetko.uidesign.ui.components.VerticalSpacerGeneral
import com.linhtetko.uidesign.ui.theme.UIDesignTheme
import com.linhtetko.uidesign.ui.theme.White
import com.linhtetko.uidesign.ui.vos.ImageSliderVO

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(modifier: Modifier = Modifier, onTapBack: () -> Unit) {

    val images by remember {
        mutableStateOf(ImageSliderVO.images)
    }
    val pagerState = rememberPagerState(initialPage = 0) { images.size }
    val verticalScrollState = rememberScrollState()

    ModalDrawerSheet(
        modifier = modifier
            .fillMaxSize(), drawerShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.space_2x),
            topEnd = dimensionResource(id = R.dimen.space_2x)
        ),
        drawerContainerColor = White,
        drawerContentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Box {
            HorizontalPager(state = pagerState) { index ->
                Image(
                    painter = painterResource(id = images[index].image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = dimensionResource(id = R.dimen.space_general))
                        .clip(
                            RoundedCornerShape(dimensionResource(id = R.dimen.space_general))
                        ),
                    contentScale = ContentScale.Crop,
                )
            }

            IconButton(
                onClick = onTapBack,
                colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.padding(20.dp).clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.space_2x))) {
            Text(text = "Zone 1")
            VerticalSpacerGeneral()
            Text(
                text = "Alligator Gar",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
            VerticalSpacerGeneral()
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.space_general))
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_directions_run_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(text = buildAnnotatedString {
                    append(
                        buildAnnotatedString {
                            append("410m away ")
                            addStyle(SpanStyle(color = MaterialTheme.colorScheme.onSurface), 0, 9)
                        }
                    )
                    append(
                        buildAnnotatedString {
                            addStyle(SpanStyle(color = MaterialTheme.colorScheme.tertiary), 0, 3)
                            append("Map")
                        }
                    )
                })
            }
            VerticalSpacerGeneral()
            Text(
                text = "With its wide, alligator-like snout and razor-sharp teeth, it's easy to see how this fish acquired its name. Despite its ferocious appearance, the alligator gar poses little threat to human beings.\n" +
                        "They prefer to lie and wait for unsuspecting prey within reach, before lunging forward to grab their prey.\n" +
                        "As the largest species in the gar family, the alligator gar can reach up to 3 metres in length.\n" +
                        "Scientists have traced this species in fossil records dating back to 100 million years ago, hence they are also known as living fossils!"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    UIDesignTheme {
        DetailScreen(
            onTapBack = {}
        )
    }
}