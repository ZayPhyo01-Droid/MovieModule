package com.movie.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.movie.api.movie.domain.model.MovieModel
import com.movie.network.imageOriginal
import com.ui.design.image.networkImagePainter

@Composable
fun LatestMovieItem(modifier: Modifier = Modifier, item: com.movie.api.movie.domain.model.MovieModel) {
    Box(modifier) {
        Image(
            painter = networkImagePainter(url = item.posterPath.imageOriginal()),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(0.2f),
                            Color.Black.copy(0.8f),
                            Color.Black
                        )
                    )
                )
        )
    }
}
