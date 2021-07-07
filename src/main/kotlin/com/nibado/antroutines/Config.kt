package com.nibado.antroutines

import java.io.File
import java.io.InputStream
import java.util.*

data class Config(
    val turnRate: Double,
    val turnChange: Double,
    val velocity: Double,
    val foodModifier: Double) {

    val foodVelocity: Double
        get() = velocity * foodModifier

    companion object {
        val RANDOM = Random()
        const val TAU = Math.PI * 2.0

        fun loadDefault() : Config = load(Config::class.java.getResourceAsStream("/default.properties"))

        fun load(file: File) : Config = file
            .also {
                if(!it.exists() || !it.isFile) {
                    throw IllegalArgumentException("${file.absolutePath} is not a valid properties file.")
                }
            }
            .inputStream().use { load(it) }

        fun load(ins: InputStream) : Config {
            val properties = Properties()
            properties.load(ins)

            return load(properties)
        }

        fun load(properties: Properties) : Config {
            return with(properties) {
                Config(
                    getDouble("turn_rate"),
                    getDouble("turn_chance"),
                    getDouble("velocity"),
                    getDouble("food_modifier"))
            }
        }

        private fun Properties.getDouble(key: String) : Double =
            getProperty(key)?.let { it.toDouble() } ?: throw IllegalArgumentException("Missing key $key")
    }
}