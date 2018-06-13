package com.justinreusch.dicerolls.src.Definitions

class Roll(die: Die, result: Int) {
  val id: String = ""
  val rollNum: Int = 0
  var die: Die = die
  var result: Int = result

  companion object {
    var rollCounter: Int = 0
  }

  init {
    this.die = die
    this.result = result
    this.id = "roll${Roll.rollCounter + 1}"
    this.rollNum = Roll.rollCounter + 1
    Roll.rollCounter += 1
  }

  fun compareTo(anotherRoll: Roll): Result {
    val ownerTag if (this.die.owner != null) {
      "${this.die.owner.name.shortName}: "
    } else {
      ""
    }
    if (this.result > anotherRoll.result) {
      println(ownerTag + "I win! :)")
      return Result.win
    } else if (this.result < anotherRoll.result) {
      println(ownerTag + "I lose! :(")
      return Result.loss
    } else {
      println(ownerTag + "We tied.")
      return Result.tie
    }
  }
  fun report(): String {
    var ownerTag: String
    if (this.die.owner != null) {
      ownerTag = "${this.die.owner.name.shortName}: "
    } else {
      ownerTag = ""
    }
    return ownerTag + "I rolled and got a ${this.result}."
  }
}
