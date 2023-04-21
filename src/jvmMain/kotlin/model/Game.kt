package model

enum class Game(val gameName: String, val abbr: String, val roster: List<Character>) {
    StreetFighter6(gameName = "Street Fighter 6", abbr = "SF6", roster = STREETFIGHTER6CAST),
    StreetFighter5(gameName = "Street Fighter 5", abbr = "SF5", emptyList());

    fun createMatchupMap(): Map<Character, Map<Character, Float>> {
        return roster.associateWith { character ->
            roster.associateWith { 5.0F }
        }
    }
}

val STREETFIGHTER6CAST = listOf<Character>(
    Character(name = "Ryu"),
    Character(name = "Chun Li"),
    Character(name = "Ken"),
)