package com.linhtetko.uidesign.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.uidesign.R
import com.linhtetko.uidesign.ui.theme.UIDesignTheme

@Composable
fun ShowCard(modifier: Modifier = Modifier, @DrawableRes image: Int, time: String, place: String) {
    Box(
        modifier = modifier.clip(
            RoundedCornerShape(dimensionResource(id = R.dimen.space_2x))
        )
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.space_2x))
                .align(Alignment.TopStart)
                .background(
                    MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(dimensionResource(id = R.dimen.space_small))
                )
                .padding(dimensionResource(id = R.dimen.space_small)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_access_time_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = time,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = place,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = dimensionResource(id = R.dimen.space_general))
                .padding(bottom = dimensionResource(id = R.dimen.space_2x)),
            color = MaterialTheme.colorScheme.surface,
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
    }
}

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    @DrawableRes image: Int,
    hasEvent: Boolean = false,
    content: String,
    @StringRes actionText: Int
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.space_2x)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(dimensionResource(id = R.dimen.space_2x)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = label), color = MaterialTheme.colorScheme.onSurface)
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
            }
            VerticalSpacerGeneral()
            Text(
                text = content,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = if (hasEvent) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium
            )
            VerticalSpacerGeneral()
            Text(
                text = stringResource(id = actionText),
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventCardPreview() {
    UIDesignTheme {
        EventCard(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.space_general)),
            image = R.drawable.ic_tickets,
            label = R.string.lbl_my_tickets,
            content = stringResource(id = R.string.lbl_there_aren_t_any_yet),
            actionText = R.string.lbl_retrieve_here
        )
    }
}