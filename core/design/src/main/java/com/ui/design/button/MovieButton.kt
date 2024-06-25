package com.ui.design.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ui.design.theme.MovieTheme


enum class MovieButtonType {
    SMALL, REGUALR
}


@Composable
fun MovieButton(
    modifier: Modifier = Modifier,
    label: String,
    type: MovieButtonType,
    onClick: () -> Unit
) {
    when (type) {
        MovieButtonType.SMALL -> {
            Button(
                onClick = { onClick() }, modifier = modifier.size(
                    100.dp, 40.dp
                )
            ) {
                Text(text = label, style = MaterialTheme.typography.bodySmall)
            }
        }

        MovieButtonType.REGUALR -> {
            // Regular button
        }
    }
}

@Preview
@Composable
private fun MovieButtonPreview() {
    MovieTheme {
        MovieButton(label = "Button", type = MovieButtonType.SMALL){}
    }
}