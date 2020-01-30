package com.legion1900.parkingproblem

import com.legion1900.parkingproblem.solutions.blockingqueue.Solution

fun main() {
    val solution = Solution()
    val time = solution.run()
    println("Solved for ${time / 1000.0} seconds")
}
