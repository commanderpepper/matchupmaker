package model

data class MatchupRow(val character: Character, val matchups: List<Matchup>)

data class Matchup(val character: Character, val winPercentage: WinPercentage = WinPercentage(percentage = 5.0))

@JvmInline
value class WinPercentage(val percentage: Double)

fun validateWinPercentage(possibleWinPercentage: String): Boolean {
    return try {
        val winPercentageAsFloat = possibleWinPercentage.toDouble()
        winPercentageAsFloat > 0 && winPercentageAsFloat < 10
    }
    catch (exception: Exception){
        false
    }
}