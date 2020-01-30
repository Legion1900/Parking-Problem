package com.legion1900.parkingproblem.solutions.semaphore

import com.legion1900.parkingproblem.solutions.base.AbsParking
import com.legion1900.parkingproblem.solutions.base.AbsSlot
import java.util.*
import java.util.concurrent.Semaphore
import kotlin.collections.ArrayList

class SemaphoreParking(slotNum: Int) : AbsParking {

    override val hasEmptySlot: Boolean
        get() = semaphore.queueLength == 0

    private val slots = ArrayList<LockSlot>().apply { for (i in 1..slotNum) add(LockSlot()) }

    private val emptySlotNum = Stack<Int>().apply { for (i in slots.indices) push(i) }

    private val semaphore = Semaphore(slotNum)

    override fun waitForSlot(): AbsSlot {
        semaphore.acquire()
        return slots[emptySlotNum.pop()]
    }

    override fun leaveSlot(freeSlot: AbsSlot) {
        val emptyAt = slots.indexOf(freeSlot)
        emptySlotNum.push(emptyAt)
        semaphore.release()
    }
}
