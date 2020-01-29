package com.legion1900.parkingproblem

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class Parking(slotNum: Int) {
    private val slots: BlockingQueue<Slot>

    init {
        val slotList = mutableListOf<Slot>().apply { for (i in 1..slotNum) add(Slot()) }
        slots = ArrayBlockingQueue(10, true, slotList)
    }

    val hasEmptySlot
        get() = slots.size > 0

    /*
    * Blocking function.
    * */
    fun waitForSlot(): Slot = slots.take()

    fun leaveSlot(freeSlot: Slot) {
        slots.add(freeSlot)
    }
}
