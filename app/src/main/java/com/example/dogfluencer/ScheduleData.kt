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

/**
 * A class which contains all the data points for a particular type of event.
 * E.g. pooping or eating
 */
class Schedule (name: String) {
    private val events = mutableListOf<Event>()
    val name = name

    fun addEvent(description: String?, name: String) {
        val time = LocalDateTime.now()
        this.events.add(Event(description, name, time))
    }

    fun getTotalEvents(): Int {
        return this.events.size
    }

    fun getMostRecentEvent(): Event {
        return this.events.last()
    }
}
