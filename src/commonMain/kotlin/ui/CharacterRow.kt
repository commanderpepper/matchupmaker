package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.Character
import model.Matchup
import model.WinPercentage
import model.validateWinPercentage

@Composable
fun CharacterRow(character: Character, matchups: List<Matchup>, onWinPercentageChange : (Character, WinPercentage) -> Unit){
    Row(modifier = Modifier.height(IntrinsicSize.Max)) {
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(text = character.name)
        }
        matchups.forEach { (opponent, winpercentage) ->
            TextField(
                modifier = Modifier.width(64f.dp),
                enabled = character != opponent,
                value = "${winpercentage.percentage}",
                maxLines = 1,
                onValueChange = {
                    if(validateWinPercentage((it))){
                        onWinPercentageChange(opponent, WinPercentage(it.toDouble()))
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Text(text = matchups.sumOf { it.winPercentage.percentage }.toString())
        }
    }
}

@Preview
@Composable
fun CharacterRowPreview(){
    val ryuCharacter = Character("Ryu", "RY")
    val matchupList = listOf(
        Matchup(
            character = ryuCharacter
        ),
        Matchup(
            character = Character("Ken", "KN")
        ),
        Matchup(
            character = Character("Chun Li", "CL")
        )
    )
    CharacterRow(ryuCharacter, matchupList){ _, _ ->

    }
}