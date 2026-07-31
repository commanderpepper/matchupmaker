package model

enum class Game(val gameName: String, val abbr: String, val roster: List<Character>) {
    StreetFighter6(gameName = "Street Fighter 6", abbr = "SF6", roster = Character.values().toList()),
    StreetFighter5(gameName = "Street Fighter 5", abbr = "SF5", roster = emptyList());

    fun createMatchupMap(): Map<Character, MutableMap<Character, Double>> {
        return roster.associateWith {
            roster.associateWith { 5.0 }.toMutableMap()
        }
    }
}
