package model

enum class Game(val gameName: String, val abbr: String, val roster: List<Character>) {
    StreetFighter6(gameName = "Street Fighter 6", abbr = "SF6", roster = STREETFIGHTER6CAST),
    StreetFighter5(gameName = "Street Fighter 5", abbr = "SF5", emptyList());

    fun createMatchupMap(): Map<Character, MutableMap<Character, Double>> {
        return roster.associateWith {
            roster.associateWith { 5.0 }.toMutableMap()
        }
    }
}

val STREETFIGHTER6CAST = listOf(
    Character(name = "Cammy"),
    Character(name = "Lily"),
    Character(name = "Zangief"),
    Character(name = "JP"),
    Character(name = "Marisa"),
    Character(name = "Manon"),
    Character(name = "Dee Jay"),
    Character(name = "E. Honda"),
    Character(name = "Dhalsim"),
    Character(name = "Blanka"),
    Character(name = "Ken"),
    Character(name = "Juri"),
    Character(name = "Kimberly"),
    Character(name = "Guile"),
    Character(name = "Chun Li"),
    Character(name = "Jamie"),
    Character(name = "Luke"),
    Character(name = "Ryu"),
)