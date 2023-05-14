package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.*

@Composable
fun MatchupScreen(characterListFlow: Flow<List<MatchupRow>>, onMatchupChange : (Character, Character, Double) -> Unit){
    val characterListState = characterListFlow.collectAsState(emptyList())
    MatchupScreen(characterListState.value, onMatchupChange)
}

@Composable
fun MatchupScreen(characterList: List<MatchupRow>, onMatchupChange : (Character, Character, Double) -> Unit){
    Column {
        characterList.forEach { matchupRow ->
            Text(text = matchupRow.character.name)
            CharacterRow(matchupRow.matchups){ otherCharacter, newWinPercentage ->
                onMatchupChange(matchupRow.character, otherCharacter, newWinPercentage.percentage)
            }
        }
    }
}

@Composable
fun CharacterRow(matchups: List<Matchup>, onWinPercentageChange : (Character, WinPercentage) -> Unit){
    Row {
        matchups.forEach { (character, winpercentage) ->
            Column {
                Text(text = character.name)
                TextField(
                    modifier = Modifier.width(64f.dp),
                    value = "${winpercentage.percentage}",
                    maxLines = 1,
                    onValueChange = {
                        if(validateWinPercentage((it))){
                            onWinPercentageChange(character, WinPercentage(it.toDouble()))
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
        }
    }
}

fun validateWinPercentage(possibleWinPercentage: String): Boolean {
    return try {
        val winPercentageAsFloat = possibleWinPercentage.toDouble()
        winPercentageAsFloat > 0 && winPercentageAsFloat < 10
    }
    catch (exception: Exception){
        false
    }
}

@Preview
@Composable
fun CharacterRowPreview(){
    val matchupList = listOf(
        Matchup(
            character = Character("Ryu", "RY"),
            winPercentage = WinPercentage(5.0)
        ),
        Matchup(
            character = Character("Ken", "KN"),
            winPercentage = WinPercentage(5.0)
        ),
        Matchup(
            character = Character("Chun Li", "CL"),
            winPercentage = WinPercentage(5.0)
        )
    )
    CharacterRow(matchupList){ _, _ ->

    }
}

@Preview
@Composable
fun MatchupScreenPreview(){
    MatchupScreen(characterListFlow = flow { Game.StreetFighter6.roster }){ _, _, _ ->

    }
}