package repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import model.Character
import model.Game
import java.time.Duration

class GameDataSourceImpl(private val game: Game) : GameDataSource {

//    private val sf6 = Game.StreetFighter6
    private val matchups : Map<Character, Map<Character, Float>> = game.createMatchupMap()

    override fun getGameMatchups(): Flow<Map<Character, Map<Character, Float>>> = flow {
        while (true){
            emit(matchups.toMap())
            delay(500L)
        }
    }
}