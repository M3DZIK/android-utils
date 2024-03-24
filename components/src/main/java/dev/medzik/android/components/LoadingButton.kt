package dev.medzik.android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * A button that shows the loading indicator, e.g., when clicked while waiting for an API response.
 *
 * @param onClick Called when this button is clicked.
 * @param modifier The [Modifier] to be applied to this button.
 * @param loading If true, a loading animation will be shown.
 * @param enabled Controls the enabled state of this button.
 */
@Composable
fun LoadingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading
    ) {
        if (loading) {
            LoadingIndicator(animating = true)
        } else {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var loading by rememberMutableBoolean(false)

        LoadingButton(
            loading = loading,
            onClick = { loading = !loading },
        ) {
            Text("Click me")
        }

        LoadingButton(
            loading = true,
            onClick = {},
        ) {
            Text("Loading")
        }
    }
}
