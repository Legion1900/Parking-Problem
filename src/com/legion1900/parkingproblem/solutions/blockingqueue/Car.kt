package com.legion1900.parkingproblem.solutions.blockingqueue

class Car(
    private val name: String = defaultName,
    private val slotUsageTime: Long = SLOT_USAGE_TIME
) {

    var state: State =
        State.ARRIVED

    fun park(slot: Slot) {
        /*
        * params:
        * designatedSlot: ??? - some type that should provide locking;
        * ??? - Slot, Slot has it`s name.
        * Slot variables reside inside Parking.
        * Parking provides mechanisms for queuing Car-s
        *
        * Also: slot - is some resource, resource doesn't now who and for how long acquired it but it provides surely
        * thread-safety measures;
        * */
        slot.useSlotFor(slotUsageTime)
    }

    override fun toString(): String {
        return "Car: $name, status: $state"
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
