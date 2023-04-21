package repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Character
import model.Game

class GameDataSourceImpl(private val coroutineScope: CoroutineScope) : GameDataSource {

    private val sf6 = Game.StreetFighter6

    override fun getGameRoster(game: Game): Flow<List<Character>> {
        TODO("Not yet implemented")
    }

    override fun getGameMatchups(game: Game): Flow<Map<Character, Float>> = flow {

    }
}