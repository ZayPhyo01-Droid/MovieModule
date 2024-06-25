package com.movie.upcoming.ui.component

import androidx.collection.longLongMapOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.movie.api.movie.domain.model.MovieModel
import com.movie.network.image
import com.ui.design.image.networkImagePainter
import com.ui.design.spacing
import com.ui.design.theme.LocalEntryPadding
import com.ui.design.theme.SubTextColor
import com.ui.design.theme.MovieTheme


@Composable
internal fun UpcomingList(
    modifier: Modifier = Modifier, upcoming: List<MovieModel>
) {
    Surface {
        Column(modifier) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacing.spaceNormal),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = spacing.genericHorizontalMargin,
                    end = spacing.genericHorizontalMargin,
                    bottom = LocalEntryPadding.current.calculateBottomPadding()
                )
            ) {
                itemsIndexed(upcoming) { i, item ->
                    UpcomingItem(
                        item
                    )
                }
            }
        }
    }
}


@Composable
private fun UpcomingItem(
    item: MovieModel
) {
    Surface() {
        Column(Modifier.fillMaxWidth()) {
            Image(
                painter = networkImagePainter(url = item.backdropPath.image()),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .aspectRatio(2f)
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    top = spacing.spaceNormal
                )
            )
            Text(
                text = "Release Date: ${item.releaseDate}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W300
                ),
                modifier = Modifier.padding(
                    top = spacing.spaceTiny
                )
            )
            Text(
                text = item.overview,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall,
                color = SubTextColor,
                modifier = Modifier.padding(
                    top = spacing.spaceMedium
                )
            )
        }
    }
}

@Preview
@Composable
private fun UpcomingItemPreview() {
    longLongMapOf()
    MovieTheme {
        UpcomingItem(
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
        )
    }
}
