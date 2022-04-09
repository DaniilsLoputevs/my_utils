package skills

import skills.abilities.Ability

class Character {
    var abilities : MutableList<Ability> = mutableListOf()
}

fun character(abilityDSL : Character.() -> Unit) : Character {
    val it = Character()
    abilityDSL.invoke(it)
    return it
}