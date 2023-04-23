package model

data class Matchup(val character: Character, val winPercentage: WinPercentage)

@JvmInline
value class WinPercentage(val percentage: Double)