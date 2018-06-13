package com.justinreusch.dicerolls.src.Definitions

class Person(val name: Name, val gender: Gender = Gender.unknown, var dice: ArrayList<Die> = ArrayList()) {

    fun assignDie(die: Die) {
        this.dice.add(die)
        die.owner = this
        println(message = "${this.name.shortName} now owns a die with ${die.sideCount.toString()} sides.")
    }

    fun identify(type: NameType = NameType.short): String {
        val personId: String = this.name.identify(type)
        val numDice: Int = this.dice.count
        val diceAdder: String = if (numDice > 1) {
            "I have $numDice dice."
        } else if (numDice == 1) {
            "I have one die."
        } else {
            ""
        }
        return "$personId $diceAdder"
    }

}

class Name(var first: String, var middle: String? = null, var last: String? = null) {

    var shortName: String = ""
    var fullName: String = ""
    var longName: String = ""

    init {
        val isMiddleName: Boolean = this.middle != null
        val isLastName: Boolean = this.last != null
        // Short name is always the first name
        this.shortName = this.first
        // Full name is the first and last (if available)
        this.fullName = if (isLastName) {
            "${this.first} ${this.last}"
        } else {
            this.first
        }
        // Long name is the first, middle and last name (if available)
        this.longName = if (isMiddleName && isLastName) {
            "${this.first} ${this.middle}! ${this.last}!"
        } else if (isMiddleName && !isLastName) {
            "${this.first} ${this.middle}!"
        } else if (isLastName && !isMiddleName) {
            "${this.first} ${this.last}!"
        } else {
            this.first
        }
    }

    fun identify(nameType: NameType = NameType.short): String {
        return when (nameType) {
            NameType.short -> "My name is ${this.shortName}."
            NameType.full -> "My name is ${this.fullName}."
            NameType.long -> "My name is ${this.longName}."
        }
    }
}

enum class NameType {
    short, full, long
}

enum class Gender {
    male, female, transgender, unknown
}