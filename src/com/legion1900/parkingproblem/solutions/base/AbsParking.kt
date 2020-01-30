package com.legion1900.parkingproblem.solutions.base

interface AbsParking {
    val hasEmptySlot: Boolean
    fun waitForSlot(): AbsSlot
    fun leaveSlot(freeSlot: AbsSlot)
}