package ui

import androidx.compose.ui.unit.dp

object GridDimens {
    val nameColumnWidth = 100.dp
    val matchupColumnWidth = 64.dp
    val totalColumnWidth = 64.dp

    // Fixed row height shared by every row in both the frozen name column and the scrollable grid,
    // so the two independently-scrolling LazyColumns stay pixel-aligned when synced via a shared
    // LazyListState (auto-measured intrinsic heights would drift between a plain Text row and a
    // TextField row).
    val rowHeight = 56.dp
}
