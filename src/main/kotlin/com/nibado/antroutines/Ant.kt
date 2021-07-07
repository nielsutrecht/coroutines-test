package com.nibado.antroutines

import kotlinx.coroutines.delay

class Ant(private val config: Config, val state: AntState) {
    suspend fun run() {
        while (true) {
            delay(100)
            think()

            println(this)
        }
    }

    private fun think() {
        if (Config.RANDOM.nextDouble() <= config.turnChange) {
            val toAdd = if (Config.RANDOM.nextBoolean()) config.turnRate else -config.turnRate
            state.addAngle(toAdd)
        }
    }

    override fun toString(): String {
        return state.toString()
    }
}