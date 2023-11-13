package com.linhtetko.uidesign.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.uidesign.R
import com.linhtetko.uidesign.ui.components.EventCard
import com.linhtetko.uidesign.ui.components.ShowCard
import com.linhtetko.uidesign.ui.components.VerticalSpacerGeneral
import com.linhtetko.uidesign.ui.theme.UIDesignTheme
import com.linhtetko.uidesign.ui.vos.ImageSliderVO
import com.linhtetko.uidesign.ui.vos.PrimaryActionVO
import com.linhtetko.uidesign.ui.vos.ShowVO
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onTapShow: () -> Unit) {

    val images by remember {
        mutableStateOf(ImageSliderVO.images)
    }
    val pagerState = rememberPagerState(initialPage = 0) { images.size }
    val verticalScrollState = rememberScrollState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppbar(onBack = { }, onTapNoti = {})
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .padding(it)
                .padding(dimensionResource(id = R.dimen.space_general))
                .background(MaterialTheme.colorScheme.surface)
        ) {
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
                        )
                        ,
                    contentScale = ContentScale.Crop,
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_2x)),
                modifier = Modifier
                    .height(250.dp)
                    .padding(dimensionResource(id = R.dimen.space_2x))
            ) {
                items(PrimaryActionVO.actions) { item ->
                    PrimaryActionIconButton(image = item.image, label = item.label, onClick = {})
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.space_general)
                )
            ) {
                EventCard(
                    modifier = Modifier.weight(1f),
                    label = R.string.lbl_my_tickets,
                    image = R.drawable.ic_tickets,
                    content = stringResource(id = R.string.lbl_there_aren_t_any_yet),
                    actionText = R.string.lbl_retrieve_here
                )
                EventCard(
                    modifier = Modifier.weight(1f),
                    label = R.string.lbl_park_hours,
                    image = R.drawable.ic_clock,
                    content = "Today, 13 Feb\n10am - 5pm",
                    hasEvent = true,
                    actionText = R.string.lbl_plan_my_visit
                )
            }
            VerticalSpacerGeneral()
            LazyRow(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_general))) {
                items(ShowVO.shows()) { item ->
                    ShowCard(
                        modifier = Modifier.clickable {
                            onTapShow()
                        },
                        image = item.image,
                        time = item.time,
                        place = item.place
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimaryActionIconButton(
    modifier: Modifier = Modifier,
    label: Int,
    image: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(dimensionResource(id = R.dimen.space_2x))
            )
        }
        VerticalSpacerGeneral()
        Text(
            text = stringResource(id = label),
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppbar(modifier: Modifier = Modifier, onBack: () -> Unit, onTapNoti: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier,
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_sea_logo),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Image(
                    painter = painterResource(id = R.drawable.ic_round_arrow_back_ios_24),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.size_navigation_icon))
                )
            }
        },
        actions = {
            IconButton(onClick = onTapNoti) {
                Image(
                    painter = painterResource(id = R.drawable.ic_noti),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.size_navigation_icon))
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    UIDesignTheme {
        HomeScreen(onTapShow = {})
    }
}