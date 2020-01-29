package com.legion1900.parkingproblem.solutions.blockingqueue

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class Solution {

    fun run(carNum: Int = CAR_NUM, slotNum: Int = PARKING_SLOTS_NUM): Long {
        return measureTimeMillis {
            val executor = Executors.newFixedThreadPool(carNum)
            val parking = Parking(slotNum)
            val cars: List<Car> = mutableListOf<Car>().apply {
                for (i in 1..CAR_NUM) add(
                    Car()
                )
            }
            val tasks = Array(carNum) { CarParkTask(cars[it], parking) }
            tasks.forEach { executor.submit(it) }
            executor.shutdown()
            executor.awaitTermination(1, TimeUnit.HOURS)
        }
    }

    private class CarParkTask(val car: Car, private val parking: Parking) : Runnable {
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

    private companion object {
        const val PARKING_SLOTS_NUM = 10
        const val CAR_NUM = 20
    }
}