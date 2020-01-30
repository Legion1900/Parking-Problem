package com.legion1900.parkingproblem.solutions.base

class Car(protected val name: String = defaultName, protected val slotUsageTime: Long = SLOT_USAGE_TIME) {

    var state: State = State.ARRIVED

    override fun toString(): String {
        return "Car: $name, status: $state"
    }

    fun park(slot: AbsSlot) {
        slot.useSlotFor(slotUsageTime)
    }

    enum class State(val msg: String) {
        ARRIVED("arrived"),
        WAITING("waiting"),
        SLOT_ACQUIRED("parked"),

        SLOT_LEFT("left")

    }

    private companion object {
        const val SLOT_USAGE_TIME = 3_000L

        var id = 0
        val defaultName: String
            get() {
                return "Car${id++}"
            }
    }
}
