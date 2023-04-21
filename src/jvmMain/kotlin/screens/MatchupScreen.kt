package screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Character
import model.Game
import model.STREETFIGHTER6CAST

@Composable
fun MatchupScreen(characterListFlow: Flow<Map<Character, Map<Character, Float>>>, onMatchupChange : (Character, Float) -> Unit){
    val characterListState = characterListFlow.collectAsState(mapOf())
    Column {
        characterListState.value.forEach { character ->
            Text(text = character.key.name)
            CharacterRow(character.value)
        }
    }
}

@Composable
fun CharacterRow(characterMap: Map<Character, Float>){
    Row {
        characterMap.forEach { (character, matchup) ->
            Column {
                Text(text = character.name)
                Text("$matchup")
            }
        }
    }
}

@Preview
@Composable
fun CharacterRowPreview(){
    CharacterRow(characterMap = Game.StreetFighter6.createMatchupMap()[STREETFIGHTER6CAST.first()] ?: mapOf())
}

@Preview
@Composable
fun MatchupScreenPreview(){
    MatchupScreen(characterListFlow = flow { Game.StreetFighter6.roster }){ character, fl ->

    }
}