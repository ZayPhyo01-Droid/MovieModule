package com.movie.home.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.lerp
import com.movie.api.movie.domain.model.MovieModel
import com.movie.home.util.TITLE_POPULAR
import com.movie.network.imageOriginal
import com.ui.design.effect.pulsate
import com.ui.design.image.networkImagePainter
import com.ui.design.spacing
import com.ui.design.theme.SubTextColor
import com.ui.design.theme.MovieTheme
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PopularMovieList(
    modifier: Modifier = Modifier,
    popularList: List<MovieModel>,
    onClickDetail: (Int) -> Unit
) {
    val popularMoviesPageState = rememberPagerState(pageCount = {
        popularList.size
    })
    Surface() {
        Column(modifier) {
            Header(
                text = TITLE_POPULAR,
                modifier = Modifier.padding(
                    bottom = spacing.spaceNormal,
                    start = spacing.genericHorizontalMargin
                )
            )
            HorizontalPager(
                state = popularMoviesPageState,
                pageSize = PageSize.Fill,
                contentPadding = PaddingValues(
                    horizontal = spacing.genericHorizontalMargin
                ),
                pageSpacing = spacing.spaceNormal
            ) {
                PopularItem(
                    item = popularList[it],
                    modifier = Modifier.graphicsLayer {
                        val pageOffset = (
                                (popularMoviesPageState.currentPage - it) + popularMoviesPageState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        scaleX = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                ){
                    onClickDetail(it)
                }
            }
        }
    }
}


@Composable
private fun PopularItem(
    modifier: Modifier = Modifier,
    item: MovieModel,
    onClickDetail: (Int) -> Unit
) {
    Surface(modifier = modifier.pulsate {
        onClickDetail(item.id)
    }, shape = MaterialTheme.shapes.medium) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = networkImagePainter(url = item.posterPath.imageOriginal()),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(500f / 750)
            )
            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .background(
                        Color.Black.copy(0.4f)
                    )
                    .fillMaxWidth()
                    .padding(
                        horizontal = spacing.spaceNormal,
                        vertical = spacing.spaceMedium
                    )
                    .wrapContentHeight()
            ) {
                Text(
                    text = item.title, color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = item.overview,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall,
                    color = SubTextColor,
                    modifier = Modifier.padding(
                        top = spacing.spaceTiny
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PopularItemPreview() {
    MovieTheme {
        PopularItem(
            item = com.movie.api.movie.domain.model.MovieModel(
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
        ){}
    }
}