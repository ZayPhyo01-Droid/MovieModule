package com.movie.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.api.movie.domain.model.MovieModel
import com.movie.home.util.TITLE_NOW_PLAYING
import com.movie.network.image
import com.ui.design.effect.pulsate
import com.ui.design.image.networkImagePainter
import com.ui.design.spacing
import com.ui.design.theme.SubTextColor
import com.ui.design.theme.MovieTheme

@Composable
internal fun NowPlayingList(
    modifier: Modifier = Modifier,
    nowPlayingList: List<MovieModel>,
    onClickDetail: (Int) -> Unit
) {
    Surface() {
        Column(modifier) {
            Header(
                text = TITLE_NOW_PLAYING,
                modifier = Modifier.padding(
                    bottom = spacing.spaceNormal,
                    start = spacing.genericHorizontalMargin
                )
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceNormal),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = spacing.genericHorizontalMargin
                )
            ) {
                itemsIndexed(nowPlayingList.take(5)) { i, item ->
                    NowPlayingItem(
                        modifier = Modifier.pulsate {
                            onClickDetail(item.id)
                        },
                        item
                    )
                }
            }
        }
    }
}


@Composable
private fun NowPlayingItem(modifier: Modifier = Modifier, model: MovieModel) {
    Surface(
        modifier = modifier
    ) {
        Column(
            Modifier
                .width(200.dp)

        ) {
            Image(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .aspectRatio(
                        1.6f
                    ),
                contentDescription = null,
                painter = networkImagePainter(
                    model.backdropPath.image()
                ),
                contentScale = ContentScale.FillBounds
            )
            Column(

            ) {
                Text(
                    text = model.title, style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(
                        top = spacing.spaceTiny
                    ),
                )
                Text(
                    text = model.overview,
                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall,
                    color = SubTextColor,
                    modifier = Modifier.padding(
                        top = spacing.spaceXXTiny
                    )
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun NowPlayingListPreview() {
    MovieTheme {
        NowPlayingList(
            nowPlayingList = listOf(
                MovieModel(
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
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun NowPlayingItemPreview() {
    MovieTheme {
        NowPlayingItem(
            model = MovieModel(
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
        )
    }
}