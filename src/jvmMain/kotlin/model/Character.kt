package model

data class Character(val name: String, val points : Float = 0.0f) {
    private val _matchups : MutableMap<Character, Float> = mutableMapOf()
    val matchups : Map<Character, Float> = _matchups
}
