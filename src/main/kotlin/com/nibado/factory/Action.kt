package com.nibado.factory

import kotlin.math.roundToInt

abstract class Action(val name: String, val start: Long, val finish: Long) {
    val duration = finish - start
    val completion: Double
        get() {
            val timeLeft = finish - System.currentTimeMillis()

            return if(timeLeft <= 0) {
                1.0
            } else {
                1.0 - (timeLeft.toDouble() / duration.toDouble())
            }
        }

    val percentage: Int
        get() = (completion * 100.0).roundToInt()

    abstract fun complete()
}