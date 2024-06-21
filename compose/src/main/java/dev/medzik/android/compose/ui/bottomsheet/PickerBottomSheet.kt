package dev.medzik.android.compose.ui.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Displaying a picker bottom sheet with a list of items.
 *
 * @param state visible state
 * @param items the list of items to display in the bottom sheet
 * @param onSelected callback function invoked when the item is selected
 * @param onDismiss function that handles the dismissal of the bottom sheet
 * @param content visual representation of each item in the picker
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> PickerBottomSheet(
    state: BottomSheetState,
    items: List<T>,
    onSelected: (T) -> Unit,
    onDismiss: () -> Unit,
    content: @Composable (T) -> Unit
) {
    val scope = rememberCoroutineScope()

    BaseBottomSheet(
        state = state,
        onDismiss = onDismiss
    ) {
        LazyColumn {
            items.forEach { item ->
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope
                                    .launch { state.hide() }
                                    .invokeOnCompletion {
                                        if (!state.sheetState.isVisible) {
                                            onSelected(item)
                                        }
                                    }
                            }
                            .height(44.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        content(item)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PickerBottomSheetPreview() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    val selectedItem = remember { mutableStateOf("Item 1") }

    val state = rememberBottomSheetState()
    val scope = rememberCoroutineScope()

    Button(
        onClick = { state.show() }
    ) {
        Text("Show")
    }

    PickerBottomSheet(
        state = state,
        items = items,
        onSelected = { selectedItem.value = it },
        onDismiss = {
            scope.launch { state.hide() }
        }
    ) { item ->
        Text(
            text = item,
            modifier = Modifier.padding(8.dp)
        )
    }
}
