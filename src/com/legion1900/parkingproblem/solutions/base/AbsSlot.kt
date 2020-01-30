package com.legion1900.parkingproblem.solutions.base

abstract class AbsSlot(val name: String = defaultName) {

    abstract fun useSlotFor(millis: Long)

    private companion object {
        var id = 0
        val defaultName
            get() = "slot${id++}"
    }
}