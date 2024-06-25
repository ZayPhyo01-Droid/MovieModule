package com.movie.api.movie.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.movie.annotations.Route
import com.movie.annotations.RouteArg
import com.movie.api.movie.domain.model.MovieModel
import com.movie.api.movie.ui.viewmodel.MovieDetailViewModel
import com.movie.network.imageOriginal
import com.ui.design.image.networkImagePainter
import com.ui.design.spacing
import com.ui.design.theme.MovieTheme

@Route(global = true)
@Composable
fun MovieDetailScreen(
    @RouteArg movieId: String,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState().value
    //Show loading
    Scaffold(
        topBar = {
            Image(
                painter = networkImagePainter(
                    url = uiState.movieDetail?.posterPath.orEmpty().imageOriginal()
                ),
                contentDescription = uiState.movieDetail?.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),

                )
        }
    ) { padding ->
        uiState.movieDetail?.let {
            MovieDetailContent(
                movieModel = it,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movieModel: MovieModel
) {
    LazyColumn(modifier = modifier) {
        item {
            Text(
                text = movieModel.title, style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(
                        horizontal = spacing.genericHorizontalMargin
                    )
                    .padding(top = spacing.spaceNormal)
            )
        }

        item {
            Text(
                text = movieModel.overview, style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(
                        horizontal = spacing.genericHorizontalMargin
                    )
                    .padding(
                        top = spacing.spaceXSmall
                    )
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
private fun MovieDetailContentPreview() {
    MovieTheme {
        MovieDetailContent(
            movieModel = MovieModel(
                adult = false,
                backdropPath = "https://example.com/backdrop.jpg",
                id = 12345,
                originalLanguage = "en",
                originalTitle = "Example Movie",
                overview = "This is a fake movie overview.",
                popularity = 45.67,
                posterPath = "https://example.com/poster.jpg",
                releaseDate = "2023-01-01",
                title = "Example Movie",
                video = false,
                voteAverage = 7.8,
                voteCount = 1234
            )
        )
    }
}