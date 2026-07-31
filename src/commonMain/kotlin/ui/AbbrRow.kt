package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.Game

private val cellBorder = Modifier.border(width = 1.dp, brush = SolidColor(value = Color.Black), shape = RectangleShape)

// The header cell above the frozen name column. Lives in its own (non-horizontally-scrolling)
// LazyColumn alongside the name column, and lines up with AbbrRow via the shared GridDimens.rowHeight.
@Composable
fun CornerCell(modifier: Modifier = Modifier) {
    GridCell(modifier = modifier, width = GridDimens.nameColumnWidth, text = "")
}

@Composable
fun AbbrRow(modifier: Modifier = Modifier, characterAbbreviations: List<String>) {
    // Opaque background so this row (used as a sticky header) doesn't let scrolled-past rows show
    // through while it's pinned above them.
    Row(modifier = modifier.background(MaterialTheme.colors.surface)) {
        characterAbbreviations.forEach { abbreviation ->
            GridCell(width = GridDimens.matchupColumnWidth, text = abbreviation)
        }
        GridCell(width = GridDimens.totalColumnWidth, text = "-/+")
    }
}

@Composable
private fun GridCell(modifier: Modifier = Modifier, width: Dp, text: String) {
    Box(
        modifier = modifier.background(MaterialTheme.colors.surface).then(cellBorder).width(width).height(GridDimens.rowHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(modifier = Modifier.padding(4.dp), text = text)
    }
}

@Preview
@Composable
fun AbbrRowPreview() {
    AbbrRow(characterAbbreviations = Game.StreetFighter6.roster.map { it.abbr })
}
