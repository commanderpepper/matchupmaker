package repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import model.Character
import model.Game
import java.time.Duration

class GameDataSourceImpl() : GameDataSource {

    private val sf6 = Game.StreetFighter6

    override fun getGameRoster(): Flow<List<Character>> = flow {
        while (true){
            delay(500L)
            emit(sf6.roster)
        }
    }

    override fun getGameMatchups(): Flow<Map<Character, Float>> = flow {

    }
}