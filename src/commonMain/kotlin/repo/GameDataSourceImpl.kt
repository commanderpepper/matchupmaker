package repo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.*
import java.util.logging.Level
import java.util.logging.Logger

class GameDataSourceImpl(game: Game) : GameDataSource {

    private val matchups : Map<Character, MutableMap<Character, Double>> = game.createMatchupMap()
    private val abbrs = game.roster.map { it.abbr }

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

    override fun updateMatchup(characterOne: Character, characterTwo: Character, newMatchup: Double) {
        val roundedMatchup = newMatchup.round()
        if(characterOne != characterTwo){
            matchups[characterOne]?.set(characterTwo, roundedMatchup)
            matchups[characterTwo]?.set(characterOne, 10 - roundedMatchup)
        }
        Logger.getLogger("Humza").log(Level.INFO, "Updated map is $matchups")
    }

    override fun getCharacterAbbreviations(): List<String> {
        return abbrs
    }
}

fun Double.round(): Double {
    return "%.1f".format(this).toDouble()
}