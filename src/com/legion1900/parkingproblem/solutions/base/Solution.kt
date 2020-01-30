package com.legion1900.parkingproblem.solutions.base

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Solution(private val parking: AbsParking) {

    fun run(carNum: Int = CAR_NUM) {
        val executor = Executors.newFixedThreadPool(carNum)
        val cars: List<Car> = mutableListOf<Car>().apply {
            for (i in 1..carNum) add(
                Car()
            )
        }
        val tasks = Array(carNum) { CarParkTask(cars[it], parking) }
        tasks.forEach { executor.submit(it) }
        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)
    }

    private class CarParkTask(private val car: Car, private val parking: AbsParking) : Runnable {
        override fun run() {
            if (!parking.hasEmptySlot) {
                car.state = Car.State.WAITING
                println(car)
            }
            car.state = Car.State.SLOT_ACQUIRED
            val slot = parking.waitForSlot()
            println("$car, at slot: ${slot.name}")
            car.park(slot)
            parking.leaveSlot(slot)
            car.state = Car.State.SLOT_LEFT
            println(car)
        }
    }

    companion object {
        const val CAR_NUM = 20
    }
}
