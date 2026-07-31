package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import model.Character
import model.Matchup
import model.WinPercentage
import model.validateWinPercentage

private val HighlightColor = Color(0xFFFFF59D)

// The leftmost cell for a row, showing the character's name. Lives in its own (non-horizontally-
// scrolling) LazyColumn alongside CornerCell, and lines up with CharacterRow's cells via the
// shared GridDimens.rowHeight.
@Composable
fun NameCell(character: Character, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.width(GridDimens.nameColumnWidth).height(GridDimens.rowHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = character.displayName)
    }
}

@Composable
fun CharacterRow(
    character: Character,
    matchups: List<Matchup>,
    focusedPair: Pair<Character, Character>? = null,
    onCellFocusChanged: (Character, Character, Boolean) -> Unit = { _, _, _ -> },
    onWinPercentageChange: (Character, WinPercentage) -> Unit,
) {
    Row {
        matchups.forEach { (opponent, winpercentage) ->
            val isHighlighted = focusedPair != null &&
                (focusedPair == character to opponent || focusedPair == opponent to character)
            MatchupCell(
                enabled = character != opponent,
                winPercentage = winpercentage,
                isHighlighted = isHighlighted,
                onFocusChanged = { isFocused -> onCellFocusChanged(character, opponent, isFocused) },
                onWinPercentageChange = { newWinPercentage -> onWinPercentageChange(opponent, newWinPercentage) }
            )
        }
        Box(
            modifier = Modifier.width(GridDimens.totalColumnWidth).height(GridDimens.rowHeight),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = matchups.sumOf { it.winPercentage.percentage }.toString())
        }
    }
}

@Composable
private fun MatchupCell(
    enabled: Boolean,
    winPercentage: WinPercentage,
    isHighlighted: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    onWinPercentageChange: (WinPercentage) -> Unit,
) {
    var text by remember { mutableStateOf(winPercentage.percentage.toString()) }
    var isFocused by remember { mutableStateOf(false) }

    // While the field is focused, the user's in-progress typing is the source of truth even if
    // it's momentarily unparsable (e.g. clearing the field, or typing a leading "0" for "0.5").
    // Only resync from the upstream value when unfocused, so this field still picks up changes
    // made elsewhere (e.g. the mirrored opponent update) without fighting active edits.
    if (!isFocused && text.toDoubleOrNull() != winPercentage.percentage) {
        text = winPercentage.percentage.toString()
    }

    TextField(
        modifier = Modifier.width(GridDimens.matchupColumnWidth).onFocusChanged {
            isFocused = it.isFocused
            onFocusChanged(it.isFocused)
        },
        enabled = enabled,
        value = text,
        maxLines = 1,
        onValueChange = { newText ->
            text = newText
            if (validateWinPercentage(newText)) {
                onWinPercentageChange(WinPercentage(newText.toDouble()))
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        colors = if (isHighlighted) {
            TextFieldDefaults.textFieldColors(backgroundColor = HighlightColor)
        } else {
            TextFieldDefaults.textFieldColors()
        }
    )
}

@Preview
@Composable
fun CharacterRowPreview(){
    val matchupList = listOf(
        Matchup(character = Character.Ryu),
        Matchup(character = Character.Ken),
        Matchup(character = Character.ChunLi)
    )
    CharacterRow(Character.Ryu, matchupList){ _, _ ->

    }
}