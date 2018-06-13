package com.justinreusch.dicerolls.src.Definitions

class RollGroup(listOfRolls: ArrayList<Roll>)  {
  var rolls: ArrayList<Roll> = ArrayList()
  val rollCount: Int
  val winningResult: Int
  val runnerUpResult: Int
  val losingResult: Int
  var winners = MutableSet<Roll> = mutableSetOf()
  var runnersUp = MutableSet<Roll> = mutableSetOf()
  var losers = MutableSet<Roll> = mutableSetOf()
  var noResults = MutableSet<Roll> = mutableSetOf()

  init {
    this.rolls = listOfRolls.sortedWith(compareBy({it.result}))
    this.rollCount = listOfRolls.count()
    let rolls = self.rolls
    // Find the high, low and runner-up values
    var highValue = rolls[0].result
    var secondHighValue = rolls[0].result
    var lowValue = rolls[0].result
    // Find just high and low on the first pass
    for (roll in rolls) {
      val currentValue = roll.result
      if currentValue > highValue {
        highValue = currentValue
      }
      if currentValue < lowValue {
        lowValue = currentValue
      }
    }
    // Use a second pass to find the runner-up
    for (roll in rolls) {
      val currentValue = roll.result
      if (currentValue > secondHighValue && currentValue < highValue) {
        secondHighValue = currentValue
      }
    }
    this.winningResult = highValue
    this.runnerUpResult = secondHighValue
    this.losingResult = lowValue
    // Assign each value to the correct set
    for (roll in rolls) {
      val currentValue = roll.result
      if (currentValue == highValue) {
        this.winners.add(roll)
      } else if currentValue == secondHighValue {
        this.runnersUp.add(roll)
      } else if currentValue == lowValue {
        this.losers.add(roll)
      } else {
        this.noResults.add(roll)
      }
    }
    // If there is more than one loser, we can move the rolls to noResult list
    val thereIsMoreThanOneLoser = this.losers.count() > 1
    if (thereIsMoreThanOneLoser) {
      for (loser in this.losers) {
        this.losers.remove(loser)
        this.noResults.add(loser)
      }
    }
  }
}