import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.Game
import org.jetbrains.skia.impl.Log
import repo.GameDataSourceImpl
import screens.MatchupScreen

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var currentGame by remember { mutableStateOf("No game chosen") }
    val games = Game.values()
    val dataSource = GameDataSourceImpl(Game.StreetFighter6)

    MaterialTheme {
        MatchupScreen(characterListFlow = dataSource.getGameMatchups()){ character, fl ->
            Log.debug("$character was changed to $fl")
        }
    }

//    MaterialTheme {
//        Column {
//            Button(onClick = {
//                text = "Hello, Desktop!"
//            }) {
//                Text(text)
//            }
//            Text(text = currentGame)
//            games.forEach {
//                Button(onClick = {currentGame = it.gameName}){
//                    Text("Choose ${it.gameName}")
//                }
//            }
//        }
//    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}