package com.legion1900.parkingproblem

import java.util.concurrent.Executors

const val PARKING_SLOTS_NUM = 10
const val CAR_NUM = 20

fun main() {
    val executor = Executors.newFixedThreadPool(CAR_NUM)
    val parking = Parking(PARKING_SLOTS_NUM)
    val cars: List<Car> = mutableListOf<Car>().apply{ for (i in 1..CAR_NUM) add(Car()) }
    val tasks = Array(CAR_NUM) { CarParkTask(cars[it], parking) }
    tasks.forEach { executor.submit(it) }
    executor.shutdown()
}

class CarParkTask(val car: Car, private val parking: Parking): Runnable {
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
