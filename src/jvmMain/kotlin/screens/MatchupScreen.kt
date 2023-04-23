package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.*

@Composable
fun MatchupScreen(characterListFlow: Flow<List<MatchupRow>>, onMatchupChange : (Character, Character, Float) -> Unit){
    val characterListState = characterListFlow.collectAsState(emptyList())
    MatchupScreen(characterListState.value, onMatchupChange)
}

@Composable
fun MatchupScreen(characterList: List<MatchupRow>, onMatchupChange : (Character, Character, Float) -> Unit){
    Column {
        characterList.forEach { matchupRow ->
            Text(text = matchupRow.character.name)
            CharacterRow(matchupRow.matchups){ otherCharacter, newWinPercentage ->
                onMatchupChange(matchupRow.character, otherCharacter, newWinPercentage.percentage)
            }
            Button(onClick = {
                onMatchupChange(matchupRow.character, matchupRow.character, 9f)
            }, content = {
                Text("Change something")
            })
        }
    }
}

@Composable
fun CharacterRow(matchups: List<Matchup>, onWinPercentageChange : (Character, WinPercentage) -> Unit){
    Row {
        matchups.forEach { (character, winpercentage) ->
            Column {
                Text(text = character.name)
                TextField(value = "${winpercentage.percentage}", onValueChange = {
                    if(validateWinPercentage((it))){
                        onWinPercentageChange(character, WinPercentage(it.toFloat()))
                    }
                })
            }
        }
    }
}

fun validateWinPercentage(possibleWinPercentage: String): Boolean {
    return try {
        val winPercentageAsFloat = possibleWinPercentage.toFloat()
        winPercentageAsFloat > 0 && winPercentageAsFloat < 10
    }
    catch (exception: Exception){
        false
    }
}

//@Preview
//@Composable
//fun CharacterRowPreview(){
//    CharacterRow(characterMap = Game.StreetFighter6.createMatchupMap()[STREETFIGHTER6CAST.first()] ?: mapOf())
//}

@Preview
@Composable
fun MatchupScreenPreview(){
    MatchupScreen(characterListFlow = flow { Game.StreetFighter6.roster }){ _, _, _ ->

    }
}