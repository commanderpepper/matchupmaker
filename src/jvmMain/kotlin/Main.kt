import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import model.Game
import repo.GameDataSourceImpl
import screens.MatchupScreen
import java.util.logging.Level
import java.util.logging.Logger

@Composable
@Preview
fun App() {
    val dataSource = GameDataSourceImpl(Game.StreetFighter6)

    MaterialTheme {
        MatchupScreen(characterListFlow = dataSource.getGameMatchups()){ characterOne, characterTwo, fl ->
            Logger.getLogger("Humza").log(Level.INFO, "$characterOne vs $characterTwo was changed to $fl")
            dataSource.updateMatchup(characterOne, characterTwo, fl)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}