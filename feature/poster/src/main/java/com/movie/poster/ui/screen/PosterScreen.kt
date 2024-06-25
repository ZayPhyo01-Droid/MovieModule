package com.movie.poster.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movie.poster.ui.component.PosterItem
import com.ui.design.theme.MovieTheme


@Composable
private fun PosterContent(
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(10) {
            PosterItem(
                modifier = Modifier.size(200.dp, 300.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PosterContentPreview() {
    MovieTheme {
        PosterContent()
    }
}
