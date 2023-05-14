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
    Character(name = "Cammy", abbr = "CA"),
    Character(name = "Lily", abbr = "LI"),
    Character(name = "Zangief", abbr = "ZA"),
    Character(name = "JP", abbr = "JP"),
    Character(name = "Marisa", abbr = "MR"),
    Character(name = "Manon", abbr = "MA"),
    Character(name = "Dee Jay", abbr = "DJ"),
    Character(name = "E. Honda", abbr = "EH"),
    Character(name = "Dhalsim", abbr = "DH"),
    Character(name = "Blanka", abbr = "BL"),
    Character(name = "Ken", abbr = "KE"),
    Character(name = "Juri", abbr = "JU"),
    Character(name = "Kimberly", abbr = "KI"),
    Character(name = "Guile", abbr = "GU"),
    Character(name = "Chun Li", abbr = "CL"),
    Character(name = "Jamie", abbr = "JA"),
    Character(name = "Luke", abbr = "LU"),
    Character(name = "Ryu", abbr = "RY"),
)