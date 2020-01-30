package com.legion1900.parkingproblem.solutions.semaphore

import com.legion1900.parkingproblem.solutions.base.AbsSlot
import java.util.concurrent.locks.ReentrantLock

class LockSlot : AbsSlot() {
    val isUsed
        get() = lock.isLocked

    private val lock = ReentrantLock()

    override fun useSlotFor(millis: Long) {
        lock.lock()
        try {
            Thread.sleep(millis)
        }
        finally {
            lock.unlock()
        }
    }
}
