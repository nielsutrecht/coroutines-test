package com.nibado.antroutines

import com.nibado.antroutines.Config.Companion.RANDOM
import com.nibado.antroutines.Config.Companion.TAU

class World(private val config: Config, val ants: List<Ant>) {
    fun tick() {
        ants.forEach { antTick(it.state) }
    }

    private fun antTick(state: AntState) {
        with(state) {
            if (RANDOM.nextDouble() <= config.turnChange) {
                val toAdd = if (RANDOM.nextBoolean()) config.turnRate else -config.turnRate
                addAngle(toAdd)
            }
            move()
        }

    }

    companion object {
        fun create(config: Config): World {
            val ants = (0..500).map {
                AntState(
                    RANDOM.nextDouble(),
                    RANDOM.nextDouble(),
                    RANDOM.nextDouble() * TAU
                )
            }.map { Ant(config, it) }
            return World(config, ants)
        }
    }
}
