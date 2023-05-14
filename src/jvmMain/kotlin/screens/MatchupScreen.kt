package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.*
import ui.AbbrRow
import ui.CharacterRow

@Composable
fun MatchupScreen(characterListFlow: Flow<List<MatchupRow>>, abbrList: List<String>, onMatchupChange : (Character, Character, Double) -> Unit){
    val characterListState = characterListFlow.collectAsState(emptyList())
    MatchupScreen(characterListState.value, abbrList, onMatchupChange)
}

@Composable
fun MatchupScreen(characterList: List<MatchupRow>, abbrList: List<String>, onMatchupChange : (Character, Character, Double) -> Unit){
    Column {
        AbbrRow(characterAbbreviations = abbrList)
        characterList.forEach { matchupRow ->
//            Text(text = matchupRow.character.name)
            CharacterRow(matchupRow.character, matchupRow.matchups){ otherCharacter, newWinPercentage ->
                onMatchupChange(matchupRow.character, otherCharacter, newWinPercentage.percentage)
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