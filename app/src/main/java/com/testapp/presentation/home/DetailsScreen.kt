package com.testapp.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.testapp.R
import com.testapp.presentation.components.ImagePager
import com.testapp.presentation.components.RatingBarItem
import com.testapp.presentation.components.TextWithArrow


@Composable
fun DetailsScreen() {
    Box {
        Column {
            ImageSection()
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                BitDetails()
                BiterSection()
            }
        }
        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimensionResource(id = R.dimen._280sdp))
                .wrapContentWidth(),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen._8sdp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(vertical = dimensionResource(id = R.dimen._8sdp))
                    .padding(horizontal = dimensionResource(id = R.dimen._16sdp))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        text = "start in",
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = "12:23:15",
                        modifier = Modifier
                            .wrapContentWidth(),
                        color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "hh  mm  ss",
                        modifier = Modifier
                            .wrapContentWidth(),
                        color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp)))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        text = "date",
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = "12:23:15",
                        modifier = Modifier
                            .wrapContentWidth(),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "hh  mm  ss",
                        modifier = Modifier
                            .wrapContentWidth(),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@Composable
fun ImageSection() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen._300sdp))
    ) {
        ImagePager()

        Text(
            text = "200 pm",
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopEnd)
                .padding(top = dimensionResource(id = R.dimen._8sdp))
                .padding(end = dimensionResource(id = R.dimen._16sdp))
                .background(
                    color = Color.Black.copy(alpha = .8f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._8sdp))
                )
                .padding(dimensionResource(id = R.dimen._6sdp)),
            color = Color.White
        )

        Column(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .align(Alignment.TopStart)
                .background(Color.Black.copy(alpha = .8f))
                .padding(dimensionResource(id = R.dimen._8sdp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))

            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))

            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))

            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
                tint = Color.White
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._16sdp)))
        }

    }
}

@Composable
fun BitDetails() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._24sdp)))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bit Name",
                style = MaterialTheme.typography.h2,
            )
            TextWithArrow(text = "Details")
        }
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._2sdp)))
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
            text = "code 1234",
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RatingBarItem(rate = 3, maxRange = 5)
            TextWithArrow(text = "Rate bit")
        }
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Current price",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = "2000$",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._16sdp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Current price",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = "2000$",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            MaterialTheme.colors.primary.copy(alpha = .4f)
                        )
                    )
                )
                .padding(dimensionResource(id = R.dimen._16sdp)),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.weight(.3f)) {
                Text(
                    text = "buy price",
                    style = MaterialTheme.typography.h2,
                )
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._2sdp)))
                Text(
                    text = "2000",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
            Button(
                modifier = Modifier
                    .weight(.3f)
                    .height(dimensionResource(id = R.dimen._30sdp)),
                onClick = { /*TODO*/ }) {
                Text(
                    text = "Buy",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))

            Text(
                modifier = Modifier
                    .weight(.3f)
                    .height(dimensionResource(id = R.dimen._30sdp))
                    .background(MaterialTheme.colors.primary.copy(alpha = .1f))
                    .wrapContentSize(Alignment.Center),
                text = "Sell",
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BiterSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen._16sdp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = "icon",
                modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),

                )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
            Text(text = "Bitters")
        }
        TextWithArrow(text = "Show all")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen._16sdp))
    ) {
        repeat((0..2).count()) {
            Bitter()
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
        }
    }
}

@Composable
fun Bitter() {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = "img",
                modifier = Modifier.size(dimensionResource(id = R.dimen._30sdp))
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
            Column {
                Text(text = "Name")
                Text(text = "100")
            }
        }

        Text(
            text = "+200",
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = MaterialTheme.colors.secondary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._8sdp))
                )
                .padding(dimensionResource(id = R.dimen._4sdp)),
            color = MaterialTheme.colors.secondary
        )
    }


}

