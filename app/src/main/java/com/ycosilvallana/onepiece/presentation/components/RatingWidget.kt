package com.ycosilvallana.onepiece.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ycosilvallana.onepiece.R
import com.ycosilvallana.onepiece.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double
) {
    val starPathString: String = stringResource(id = R.string.star_path)
    val starPath: Path = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds: Rect = remember {
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBounds = starPathBounds)
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 2F
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = this.size
        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height
            val left = (canvasSize.width / 2F) - (pathWidth / 1.7F)
            val top = (canvasSize.height / 2F) - (pathHeight / 1.7F)
            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FilledStarPreview() {
    RatingWidget(modifier = Modifier, rating = 1.0)
}
