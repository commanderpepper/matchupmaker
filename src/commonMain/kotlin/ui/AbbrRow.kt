package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import model.Game

@Composable
fun AbbrRow(modifier: Modifier = Modifier, characterAbbreviations: List<String>) {
    val cellModifier =
        Modifier.border(width = 1.dp, brush = SolidColor(value = Color.Black), shape = RectangleShape).padding(4.dp)
    Row(modifier = modifier) {
        Text(modifier = cellModifier.width(94.dp), text = "\t\t")
        characterAbbreviations.forEach { abbreviation ->
            Text(modifier = cellModifier.width(50.dp), text = abbreviation)
        }
        Text(modifier = cellModifier, text = "-/+")
    }
}

@Preview
@Composable
fun AbbrRowPreview() {
    AbbrRow(characterAbbreviations = Game.StreetFighter6.roster.map { it.abbr })
}
