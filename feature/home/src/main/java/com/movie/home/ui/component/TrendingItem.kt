package com.movie.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.api.movie.domain.model.MovieModel
import com.movie.home.util.TITLE_TRENDING_PLAYING
import com.movie.network.image
import com.ui.design.effect.pulsate
import com.ui.design.image.networkImagePainter
import com.ui.design.spacing
import com.ui.design.theme.MovieTheme

@Composable
internal fun TrendingList(
    modifier: Modifier = Modifier,
    trendingList: List<MovieModel>,
    onClickDetail: (Int) -> Unit
) {
    Surface() {
        Column(modifier) {
            Header(
                text = TITLE_TRENDING_PLAYING,
                modifier = Modifier.padding(
                    bottom = spacing.spaceNormal,
                    start = spacing.genericHorizontalMargin
                )
            )
            LazyRow(
                contentPadding = PaddingValues(
                    horizontal = spacing.genericHorizontalMargin
                ),
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceNormal),
            ) {
                items(trendingList.take(5)) {
                    TrendingItem(item = it) {
                        onClickDetail(it)
                    }
                }
            }
        }
    }
}


@Composable
private fun TrendingItem(item: MovieModel, onClickDetail: (Int) -> Unit) {
    Column(
        Modifier
            .width(200.dp)
            .pulsate { onClickDetail(item.id) }) {
        Image(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)

                .aspectRatio(500 / 750f)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium),
            painter = networkImagePainter(url = item.posterPath.image()),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(

        ) {
            Text(
                text = item.title, style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    top = spacing.spaceTiny
                ),
                maxLines = 2
            )
        }
    }


}

@Preview
@Composable
private fun TrendingItemPreview() {
    MovieTheme {
        TrendingItem(
            item = MovieModel(
                adult = false,
                backdropPath = "/fake_backdrop_path.jpg",
                id = 12345,
                originalLanguage = "en",
                originalTitle = "Fake Original Title",
                overview = "This is a fake movie overview.",
                popularity = 7.8,
                posterPath = "/fake_poster_path.jpg",
                releaseDate = "2023-01-01",
                title = "Fake Movie Title",
                video = false,
                voteAverage = 8.5,
                voteCount = 1000
            )
        ) {}
    }
}

