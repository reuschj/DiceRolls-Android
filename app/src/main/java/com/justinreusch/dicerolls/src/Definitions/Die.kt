package com.justinreusch.dicerolls.src.Definitions
import java.util.Random

class Die(sides: ArrayList<Int>, owner: Person? = null) {
  val id: String = ""
  val dieNum: Int = 0
  val sides: ArrayList<Int> = ArrayList()
  val sideCount: Int = 0
  var owner: Person? = null
  var name: String = ""

  companion object {
    var countOfDice: Int = 0
  }


  init {
    this.sides = sides
    this.sideCount = sides.count()
    var ownerNameForID: String = "Nobody"
    if (owner != null) {
      this.owner = owner
      ownerNameForID = owner.name.shortName
      this.name = "${ownerNameForID}'s die with ${sides.count()} sides"
    } else {
      this.owner = null
      this.name = "A unowned die with ${sides.count()} sides"
    }
    var sideStringForID = ""
    for (side in sides) {
      sideStringForID += side.toString()
    }
    val indexOfThisDie = Die.countOfDice + 1
    this.id = "$ownerNameForID ${sides.count()}_$sideStringForID $indexOfThisDie"
    this.dieNum = indexOfThisDie
    // Assign the die to it's owner
    if (owner != null) {
      owner.assignDie(this)
    }
    Die.countOfDice += 1
  }

  fun assignOwner(owner: Person) {
    this.owner = owner
    owner.assignDie(this)
    println("The die with ${this.sideCount} sides now belongs to ${owner.name.shortName}.")
  }

  fun roll(): Roll {
    var randomGenerator = Random()
    val rolledSide: Int = randomGenerator.nextInt(this.sideCount - 1) + 1
    val rollResult: Roll = Roll(this, this.sides[rolledSide])
    return rollResult
  }

  fun identify(): String {
    var ownerName = if (owner != null) {
       owner.name.shortName
    } else {
      "nobody"
    }
    val returnString: String = "I am a die belonging to $ownerName with ${this.sideCount} sides: ${buildListToString(this.sides)}"
    return returnString
  }

}