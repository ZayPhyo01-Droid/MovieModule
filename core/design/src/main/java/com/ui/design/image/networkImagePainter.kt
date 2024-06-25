package com.ui.design.image

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ui.design.R

@Composable
fun networkImagePainter(
    url: String,
    @DrawableRes placeHolder: Int = R.drawable.ic_placeholder_default
): Painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .crossfade(true)
        .crossfade(400)
        .error(placeHolder)
        .placeholder(placeHolder)
        .build(),
    contentScale = ContentScale.FillBounds
)