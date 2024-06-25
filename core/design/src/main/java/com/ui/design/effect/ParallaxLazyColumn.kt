package com.ui.design.effect

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ParallaxLazyColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    background: @Composable () -> Unit,
    foreground: @Composable () -> Unit,
    collapseContent: @Composable () -> Unit = {},
    itemContent: LazyListScope.() -> Unit,
    topItemHeight: Dp,
    color: Color = Color.Transparent,
    contentPadding: PaddingValues = PaddingValues()

) {

    val density = LocalDensity.current
    val topBarHeight = with(density) {
        topItemHeight.toPx()
    }
    var deltaY by rememberSaveable() {
        mutableIntStateOf(0)
    }
    val showCollapsedContent = remember() {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .nestedScroll(remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        val dy = available.y.roundToInt()
                        val newDeltaY = dy + deltaY
                        if (listState.firstVisibleItemIndex == 0)
                            deltaY = newDeltaY
                                .coerceIn(-topBarHeight.toInt(), 0)

                        return Offset.Zero
                    }
                }
            })
    ) {
        Box(
            Modifier
                .graphicsLayer {
                    translationY = deltaY / 2f
                }) {
            background()
        }
        Box(
            Modifier.fillMaxWidth()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.background(
                    color
                ),
                contentPadding = contentPadding
            ) {
                item("topBar") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(density.run { (topBarHeight).toDp() })
                    ) {
                        foreground()
                    }
                }
                itemContent()
            }
            AnimatedVisibility(showCollapsedContent.value,
                enter = slideInVertically { -it }, exit = slideOutVertically { -it }) {
                collapseContent()
            }
        }
    }
}