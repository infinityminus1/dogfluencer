package com.example.dogfluencer

import java.time.LocalDateTime

val schedules = mutableMapOf<String, Schedule>()
var eventTypes = mutableListOf(
    Pair("Poop", 0x1F4A9),
    Pair("Pee", 0x1F4A6),
    Pair("Sleep", 0x1F4A4),
    Pair("Food", 0x1F354)
)

data class Event(val description: String?, val name: String, val time: LocalDateTime)

class Schedule {
    private val timeDataPoints = mutableListOf<Event>()
    fun addEvent(description: String?, name: String, time: LocalDateTime) {
        this.timeDataPoints.add(Event(description, name, time))
    }
}

fun addEvent(name: String, description: String?){
    val time = LocalDateTime.now()
    schedules[name]?.addEvent(description, name, time)
}

fun generateSchedules() {
    for (eventType in eventTypes) {
        schedules[eventType.first] = Schedule()
    }
}
