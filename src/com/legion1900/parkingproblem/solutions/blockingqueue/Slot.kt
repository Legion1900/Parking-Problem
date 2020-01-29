package com.legion1900.parkingproblem.solutions.blockingqueue

class Slot(val name: String = defaultName) {

    /*
    * Blocking function.
    * */
    @Synchronized
    fun useSlotFor(millis: Long) {
        Thread.sleep(millis)
    }

    private companion object {
        var id = 0
        val defaultName
            get() = "slot${id++}"
    }
}
