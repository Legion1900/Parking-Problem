package com.legion1900.parkingproblem.solutions.blockingqueue

import com.legion1900.parkingproblem.solutions.base.AbsParking
import com.legion1900.parkingproblem.solutions.base.AbsSlot
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class BlockingQueueParking(slotNum: Int) : AbsParking {
    override val hasEmptySlot
        get() = slots.size > 0

    private val slots: BlockingQueue<AbsSlot>

    init {
        val slotList = mutableListOf<SyncSlot>().apply { for (i in 1..slotNum) add(SyncSlot()) }
        slots = ArrayBlockingQueue(10, true, slotList)
    }

    /*
    * Blocking function.
    * */
    override fun waitForSlot(): AbsSlot = slots.take()

    override fun leaveSlot(freeSlot: AbsSlot) {
        slots.add(freeSlot)
    }
}
