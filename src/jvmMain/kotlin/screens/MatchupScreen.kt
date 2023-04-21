package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import model.Character

@Composable
fun MatchupScreen(characterListFlow: Flow<List<Character>>, onMatchupChange : (Character, Float) -> Unit){
    val characterListState = characterListFlow.collectAsState(emptyList())
    Column {
        characterListState.value.forEach { character ->
            Text(text = character.name)
            Row {
                character.matchups.forEach { matchup ->
                    Column {
                        Text(text = "Matchup against ${matchup.key.name} is ${matchup.value}")
                        TextField(value = "5", onValueChange = {
                            if(it as? Float != null){
                                onMatchupChange(matchup.key, it.toFloat())
                            }
                        })
                    }
                }
            }
        }
    }
}