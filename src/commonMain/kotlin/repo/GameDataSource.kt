package repo

import kotlinx.coroutines.flow.Flow
import model.Character
import model.MatchupRow

interface GameDataSource {
    fun getGameMatchups(): Flow<List<MatchupRow>>

    fun updateMatchup(characterOne: Character, characterTwo: Character, newMatchup: Double)

    fun getCharacterAbbreviations(): List<String>
}