package repo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.*
import java.util.logging.Level
import java.util.logging.Logger

class GameDataSourceImpl(private val game: Game) : GameDataSource {

    private val matchups : Map<Character, MutableMap<Character, Float>> = game.createMatchupMap()

    override fun getGameMatchups(): Flow<List<MatchupRow>> = flow {
        while (true){
            emit(matchups.map {
                MatchupRow(character = it.key, matchups = it.value.map {
                    Matchup(it.key, WinPercentage(it.value))
                })
            })
            delay(500L)
        }
    }

    override fun updateMatchup(characterOne: Character, characterTwo: Character, newMatchup: Float) {
        matchups[characterOne]?.set(characterTwo, newMatchup)
        Logger.getLogger("Humza").log(Level.INFO, "Updated map is $matchups")
    }
}