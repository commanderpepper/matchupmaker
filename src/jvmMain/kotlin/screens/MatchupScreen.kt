package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.*
import ui.AbbrRow
import ui.CharacterRow
import ui.CornerCell
import ui.NameCell

@Composable
fun MatchupScreen(characterListFlow: Flow<List<MatchupRow>>, abbrList: List<String>, onMatchupChange : (Character, Character, Double) -> Unit){
    val characterListState = characterListFlow.collectAsState(emptyList())
    MatchupScreen(characterListState.value, abbrList, onMatchupChange)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchupScreen(characterList: List<MatchupRow>, abbrList: List<String>, onMatchupChange : (Character, Character, Double) -> Unit){
    // The pair currently being edited, so both a cell and its mirrored (opponent's-view) cell can
    // be highlighted together.
    var focusedPair by remember { mutableStateOf<Pair<Character, Character>?>(null) }

    // Two side-by-side LazyColumns sharing one LazyListState: the left one (name column, fixed
    // width, no horizontal scroll) and the right one (the grid) always report the same
    // firstVisibleItemIndex/scrollOffset, so scrolling either one vertically keeps them in
    // lockstep. This is what makes the name column "frozen" while the grid scrolls horizontally
    // independently. Both use identical row heights (GridDimens.rowHeight) so the sync stays
    // pixel-aligned.
    val listState = rememberLazyListState()

    Row {
        LazyColumn(state = listState) {
            stickyHeader { CornerCell() }
            items(characterList, key = { it.character.abbr }) { matchupRow ->
                NameCell(matchupRow.character)
            }
        }
        LazyColumn(state = listState, modifier = Modifier.horizontalScroll(rememberScrollState())) {
            stickyHeader {
                AbbrRow(characterAbbreviations = abbrList)
            }
            items(characterList, key = { it.character.abbr }) { matchupRow ->
                CharacterRow(
                    character = matchupRow.character,
                    matchups = matchupRow.matchups,
                    focusedPair = focusedPair,
                    onCellFocusChanged = { rowCharacter, opponent, isFocused ->
                        focusedPair = when {
                            isFocused -> rowCharacter to opponent
                            focusedPair == rowCharacter to opponent -> null
                            else -> focusedPair
                        }
                    }
                ) { otherCharacter, newWinPercentage ->
                    onMatchupChange(matchupRow.character, otherCharacter, newWinPercentage.percentage)
                }
            }
        }
    }
}

@Preview
@Composable
fun MatchupScreenPreview(){
    MatchupScreen(abbrList = Game.StreetFighter6.roster.map { it.abbr }, characterListFlow = flow { Game.StreetFighter6.roster }){ _, _, _ ->

    }
}