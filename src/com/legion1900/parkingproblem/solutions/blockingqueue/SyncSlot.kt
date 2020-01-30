package com.legion1900.parkingproblem.solutions.blockingqueue

import com.legion1900.parkingproblem.solutions.base.AbsSlot

class SyncSlot : AbsSlot() {
    /*
    * Blocking function.
    * */
    @Synchronized
    override fun useSlotFor(millis: Long) {
        Thread.sleep(millis)
    }
}
