package com.legion1900.parkingproblem

import com.legion1900.parkingproblem.solutions.base.Solution
import com.legion1900.parkingproblem.solutions.blockingqueue.BlockingQueueParking
import com.legion1900.parkingproblem.solutions.semaphore.SemaphoreParking

fun main() {
    val semaphoreSolution = Solution(SemaphoreParking(10))
    val blockingQueueSolution = Solution(BlockingQueueParking(10))
    println("_______Solution on Semaphore_______")
    semaphoreSolution.run()
    println("_______Solution on BlockingQueue_______")
    blockingQueueSolution.run()
}
