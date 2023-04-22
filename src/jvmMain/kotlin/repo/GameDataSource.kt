package repo

import kotlinx.coroutines.flow.Flow
import model.Character
import model.Game

interface GameDataSource {
    fun getGameMatchups(): Flow<Map<Character, Map<Character, Float>>>

    fun updateMatchup(characterOne: Character, characterTwo: Character, newMatchup: Float)
}