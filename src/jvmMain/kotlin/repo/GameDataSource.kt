package repo

import kotlinx.coroutines.flow.Flow
import model.Character
import model.Game

interface GameDataSource {
    fun getGameRoster(): Flow<List<Character>>

    fun getGameMatchups(): Flow<Map<Character, Float>>
}