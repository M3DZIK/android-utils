package dev.medzik.android.components.icons

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.components.rememberMutableBoolean
import dev.medzik.android.components.ui.IconBox

/**
 * Displays a visibility icon based on the provided boolean value.
 *
 * @param modifier the [Modifier] to be applied to the icon
 * @param visibility determines which icon should be displayed
 */
@Composable
fun VisibilityIcon(
    modifier: Modifier = Modifier,
    visibility: Boolean
) {
    val iconVector = when (visibility) {
        true -> Icons.Default.VisibilityOff
        false -> Icons.Default.Visibility
    }

    Crossfade(
        modifier = modifier,
        targetState = iconVector,
        label = "VisibilityIcon"
    ) { icon ->
        IconBox(imageVector = icon)
    }
}

@Preview
@Composable
fun VisibilityIconPreview() {
    var visibility by rememberMutableBoolean()

    VisibilityIcon(
        modifier = Modifier.clickable {
            visibility = !visibility
        },
        visibility = visibility
    )
}