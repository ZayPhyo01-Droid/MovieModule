package com.ui.design.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun LoadingDialog(
    onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Loading()
    }
}


@Composable
private fun Loading(
) {
    Box(modifier = Modifier.size(80.dp)) {
        CircularProgressIndicator()
    }
}

