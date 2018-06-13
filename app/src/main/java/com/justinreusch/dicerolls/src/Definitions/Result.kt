package com.justinreusch.dicerolls.src.Definitions

enum class Result(val rawValue: Int) {
  loss(-1),
  none(0),
  tie(1),
  win(2)
}
