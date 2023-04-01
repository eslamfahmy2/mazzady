package com.testapp.presentation.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.testapp.R


@ExperimentalFoundationApi
@Composable
fun NoScrollEffect(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        content()
    }
}

@Composable
fun RatingBarItem(
    maxRange: Int,
    rate: Int
) {
    Row(
        modifier = Modifier
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        (0..maxRange).forEach { index ->
            Icon(
                imageVector = Icons.Default.Star,
                tint = if (index <= rate + 1) MaterialTheme.colors.secondary else Color.Gray,
                contentDescription = "star",
                modifier = Modifier.size(dimensionResource(id = R.dimen._16sdp))
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp)))
        }
    }
}


@Composable
fun TextWithArrow(
    text: String,
    icon: ImageVector = Icons.Default.KeyboardArrowRight,
) {
    Row(
        modifier = Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary
        )
        Icon(
            icon,
            contentDescription = "arrow",
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.width(dimensionResource(id = R.dimen._20sdp))
        )
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun ImagePager() {

    val pagerState = rememberPagerState()

    Box(Modifier.fillMaxSize()) {

        NoScrollEffect {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                count = 4,
                verticalAlignment = Alignment.Top,
                state = pagerState
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sunny),
                    contentDescription = "image",
                    modifier = Modifier.fillMaxSize(),
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_play_circle_outline_24),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = .4f)),
                    contentScale = ContentScale.Inside
                )

            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen._24sdp))
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._4sdp))
                )
                .padding(dimensionResource(id = R.dimen._8sdp)),
            activeColor = Color.White,
            inactiveColor = MaterialTheme.colors.onPrimary,
            indicatorWidth = dimensionResource(id = R.dimen._4sdp),
            indicatorHeight = dimensionResource(id = R.dimen._4sdp),
            indicatorShape = CircleShape
        )
    }
}

@Composable
fun Scrim(
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.32f),
    onDismiss: () -> Unit,
    visible: Boolean
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec()
        )
        val closeSheet = "2"
        val dismissModifier = if (visible) {
            Modifier
                .pointerInput(onDismiss) { detectTapGestures { onDismiss() } }
                .semantics(mergeDescendants = true) {
                    contentDescription = closeSheet
                    onClick { onDismiss(); true }
                }
        } else {
            Modifier
        }

        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissModifier)
        ) {
            drawRect(color = color, alpha = alpha)
        }
    }
}
