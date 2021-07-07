package com.nibado.antroutines

import com.nibado.antroutines.Config.Companion.TAU
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin

data class AntState(
    var x: Double,
    var y: Double,
    var angle: Double,
    var velocity: Double = 0.003,
    var hasFood: Boolean = false) {

    fun addAngle(angle: Double) {
        this.angle += angle
        if(this.angle > TAU) {
            this.angle -= TAU
        } else if(this.angle < 0) {
            this.angle += TAU
        }
    }

    fun move() {
        x += cos(angle) * velocity
        y += sin(angle) * velocity

        if (x < 0 || x > 1.0) {
            angle = PI - angle;
        }
        else if (y < 0 ||y > 1.0) {
            angle = (PI * 2) - angle;
        }

        if(Math.random() <= 0.01) {
            hasFood = !hasFood
        }
    }
}
